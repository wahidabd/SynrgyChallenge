package com.wahidabd.synrgy.utils

import android.content.Context
import com.wahidabd.synrgy.domain.auth.AuthUser


/**
 * Created by Wahid on 10/26/2023.
 * Github github.com/wahidabd.
 */


class SharedPreferences(context: Context) {

    private val prefs = context.getSharedPreferences(Constants.PREF_SHARED, Context.MODE_PRIVATE)

    fun register(user: AuthUser) {
        prefs.edit().apply {
            putString(Constants.PREF_NAME, user.name)
            putString(Constants.PREF_EMAIL, user.email)
            putString(Constants.PREF_PASSWORD, user.password)
        }.apply()
    }

    fun login(email: String, password: String): Boolean {
        val emailPref = prefs.getString(Constants.PREF_EMAIL, "")
        val passwordPref = prefs.getString(Constants.PREF_PASSWORD, "")
        return email == emailPref && password == passwordPref
    }

    fun getUser(): AuthUser {
        val name = prefs.getString(Constants.PREF_NAME, "")
        val email = prefs.getString(Constants.PREF_EMAIL, "")
        val password = prefs.getString(Constants.PREF_PASSWORD, "")
        return AuthUser(name.toString(), email.toString(), password.toString())
    }

    fun setLogin(value: Boolean) {
        prefs.edit().putBoolean(Constants.PREF_IS_LOGIN, value).apply()
    }

    fun getLogin(): Boolean {
        return prefs.getBoolean(Constants.PREF_IS_LOGIN, false)
    }

    fun logout() {
        prefs.edit().clear().apply()
    }
}