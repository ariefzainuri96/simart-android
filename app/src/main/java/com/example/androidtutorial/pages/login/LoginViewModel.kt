package com.example.androidtutorial.pages.login

import android.app.Application
import androidx.datastore.preferences.core.edit
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidtutorial.data.repository.LoginRepository
import com.example.androidtutorial.pages.login.model.LoginFormModel
import com.example.androidtutorial.utils.PreferenceKeys
import com.example.androidtutorial.utils.RequestState
import com.example.androidtutorial.utils.userPreferencesDataStore
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repository: LoginRepository,
    private val application: Application
): ViewModel() {
    private var _loginForm = MutableStateFlow(LoginFormModel())
    val loginForm = _loginForm.asStateFlow()

    private var _loginState = MutableSharedFlow<RequestState>()
    val loginState = _loginState.asSharedFlow()

    val loginHandler = CoroutineExceptionHandler { _, throwable ->
        println("Error happening, Error: $throwable")
    }

    fun updateLoginForm(data: LoginFormModel) {
        _loginForm.value = data
    }

    fun login() {
        println("User -> ${loginForm.value}")

        viewModelScope.launch(loginHandler) {
            _loginState.emit(RequestState.LOADING)

            delay(1000L)

            application.applicationContext.userPreferencesDataStore.edit { preferences ->
                preferences[PreferenceKeys.IS_LOGIN] = true
            }

            _loginState.emit(RequestState.SUCCESS)
        }
    }

    private fun checkAuthenticatedUser() {
        application.applicationContext.userPreferencesDataStore.data.map {
            val isLogin = it[PreferenceKeys.IS_LOGIN]
            println("Is User Authenticated -> $isLogin")
            _loginState.emit(if (isLogin == true) RequestState.SUCCESS else RequestState.IDLE)
        }
    }

    init {
        checkAuthenticatedUser()
    }
}