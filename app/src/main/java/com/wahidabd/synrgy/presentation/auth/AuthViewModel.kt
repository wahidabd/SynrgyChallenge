package com.wahidabd.synrgy.presentation.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wahidabd.synrgy.domain.auth.AuthUser
import com.wahidabd.synrgy.utils.AuthDataStore
import com.wahidabd.synrgy.utils.isValidEmail
import kotlinx.coroutines.launch


/**
 * Created by wahid on 11/6/2023.
 * Github github.com/wahidabd.
 */


class AuthViewModel(private val pref: AuthDataStore) : ViewModel() {

    val isLogin = pref.getLogin()

    private val _user = MutableLiveData<AuthUser>()
    val user: LiveData<AuthUser> get() = _user

    private val _isLoginSuccess = MutableLiveData<Boolean>()
    val isLoginSuccess: LiveData<Boolean> get() = _isLoginSuccess


    fun setUser(auth: AuthUser) {
        viewModelScope.launch {
            pref.setUser(auth)
        }
    }

    fun getUser(email: String, password: String) {
        viewModelScope.launch {
            pref.getUser(email, password).collect {
                _isLoginSuccess.value = it
            }
        }
    }

    fun setLogin(isLogin: Boolean) {
        viewModelScope.launch {
            pref.setLogin(isLogin)
        }
    }

    fun getUserInformation() {
        viewModelScope.launch {
            pref.getUserInformation().collect {
                _user.value = it
            }
        }
    }

    fun validateRegister(name: String, email: String, password: String): Boolean {
        return email.isNotBlank()
                && password.isNotBlank()
                && name.isNotBlank()
                && password.length >= 6
                && email.isValidEmail()
    }

    fun validateLogin(email: String, password: String): Boolean {
        return email.isNotBlank()
                && password.isNotBlank()
                && password.length >= 6
                && email.isValidEmail()
    }
}