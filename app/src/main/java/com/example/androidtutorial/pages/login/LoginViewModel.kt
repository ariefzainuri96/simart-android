package com.example.androidtutorial.pages.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginForm(
    var username: String = "",
    var password: String = "",
    var checkbox: Boolean = false,
) {
    fun enableLoginButton(): Boolean {
        return username.isNotEmpty() && password.isNotEmpty()
    }
}

class LoginViewModel: ViewModel() {
    var loginForm = MutableLiveData(LoginForm())
        private set

    fun updateLoginForm(data: MutableLiveData<LoginForm>) {
        loginForm.value = data.value
    }
}