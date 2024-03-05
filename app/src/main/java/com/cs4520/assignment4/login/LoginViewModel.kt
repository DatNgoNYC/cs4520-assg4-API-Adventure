package com.cs4520.assignment4.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel() {
    private var _loginSuccess = MutableLiveData<Boolean>()
    var loginSuccess: LiveData<Boolean> = _loginSuccess

    fun login(username: String, password: String) {
        if (username === "admin" && password === "admin") {
            _loginSuccess.value = true
        }
    }
}