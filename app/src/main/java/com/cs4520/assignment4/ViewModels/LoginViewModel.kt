package com.cs4520.assignment4.ViewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel() {
    private var _loginSuccess = MutableLiveData<Boolean>(false)
    var loginSuccess: LiveData<Boolean> = _loginSuccess

    fun login(username: String, password: String) {
        _loginSuccess.value = username == "admin" && password == "admin"
    }
}