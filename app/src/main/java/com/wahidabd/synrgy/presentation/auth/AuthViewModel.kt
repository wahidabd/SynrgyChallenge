package com.wahidabd.synrgy.presentation.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wahidabd.synrgy.domain.auth.AuthUser
import com.wahidabd.synrgy.utils.AuthDataStore
import kotlinx.coroutines.launch


/**
 * Created by wahid on 11/6/2023.
 * Github github.com/wahidabd.
 */


class AuthViewModel(private val pref: AuthDataStore) : ViewModel() {

    private val _user = MutableLiveData<AuthUser>()
    val user: LiveData<AuthUser> get() = _user

    private val _isLogin = MutableLiveData<Boolean>(false)
    val isLogin: LiveData<Boolean> get() = _isLogin

    fun setUser(auth: AuthUser) {
        viewModelScope.launch {
            pref.setUser(auth)
        }
    }

    fun getUser() {
        viewModelScope.launch {
            pref.getUser().collect {
                _user.value = it
            }
        }
    }

    fun setLogin(isLogin: Boolean) {
        viewModelScope.launch {
            pref.setLogin(isLogin)
        }
    }

    fun getLogin() {
        viewModelScope.launch {
            pref.getLogin().collect {
                _isLogin.value = it
            }
        }
    }

}