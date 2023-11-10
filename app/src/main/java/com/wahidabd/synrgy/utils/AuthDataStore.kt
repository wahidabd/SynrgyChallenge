package com.wahidabd.synrgy.utils

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.wahidabd.synrgy.domain.auth.AuthUser
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


/**
 * Created by wahid on 11/6/2023.
 * Github github.com/wahidabd.
 */


class AuthDataStore(context: Context) {

    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = Constants.PREF_AUTH)
    private val dataStore = context.dataStore

    suspend fun setLogin(isLogin: Boolean){
        dataStore.edit { preferences ->
            preferences[IS_LOGIN] = isLogin
        }
    }

    fun getLogin(): Flow<Boolean> =
        dataStore.data.map { preferences ->
            preferences[IS_LOGIN] ?: false
        }

    suspend fun setUser(auth: AuthUser){
        dataStore.edit { preferences ->
            preferences[NAME] = auth.name
            preferences[EMAIL] = auth.email
            preferences[PASSWORD] = auth.password
        }
    }

    fun getUser(email: String, password: String): Flow<Boolean> = dataStore.data.map { preferences ->
        val data = AuthUser(
            preferences[NAME] ?: "",
            preferences[EMAIL] ?: "",
            preferences[PASSWORD] ?: ""
        )

        data.email == email && data.password == password
    }

    fun getUserInformation(): Flow<AuthUser> = dataStore.data.map { preferences ->
        AuthUser(
            preferences[NAME] ?: "",
            preferences[EMAIL] ?: "",
            preferences[PASSWORD] ?: ""
        )
    }


    companion object {
        private val NAME = stringPreferencesKey(Constants.PREF_NAME)
        private val EMAIL = stringPreferencesKey(Constants.PREF_EMAIL)
        private val PASSWORD = stringPreferencesKey(Constants.PREF_PASSWORD)
        private val IS_LOGIN = booleanPreferencesKey(Constants.PREF_IS_LOGIN)

        @Volatile
        private var INSTANCE: AuthDataStore? = null

        fun getInstance(context: Context): AuthDataStore {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = AuthDataStore(context)
                    INSTANCE = instance
                }
                return instance
            }
        }
    }

}