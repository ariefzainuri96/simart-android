package simart.umby.android.pages.login

import android.app.Application
import androidx.datastore.preferences.core.edit
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import simart.umby.android.data.repository.LoginRepository
import simart.umby.android.pages.login.model.LoginFormModel
import simart.umby.android.utils.PreferenceKeys
import simart.umby.android.utils.RequestState
import simart.umby.android.utils.dataStore
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

    fun updateLoginForm(update: LoginFormModel.() -> LoginFormModel) {
        _loginForm.update {
            it.update()
        }
    }

    fun login() {
        viewModelScope.launch(loginHandler) {
            _loginState.emit(RequestState.LOADING)

            delay(1000L)

            application.applicationContext.dataStore.edit { preferences ->
                preferences[PreferenceKeys.Companion.IS_LOGIN] = true
            }

            _loginState.emit(RequestState.SUCCESS)
        }
    }
}