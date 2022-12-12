package com.example.clean_architecture_mvvm.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.LoginUseCase
import com.example.domain.models.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class LoginVM @Inject constructor(private val loginUseCase: LoginUseCase) : ViewModel() {

    private val _loginUiFlow = MutableStateFlow<LoginUiState>(LoginUiState.None)
    val loginFlow = _loginUiFlow.asStateFlow()

    fun loginRequest(email: String, password: String) {
        val result = loginUseCase.executeLoginRequest(email, password)
        result
            .onStart { _loginUiFlow.value = LoginUiState.Loading }
            .onEach {
                it.onSuccess { _loginUiFlow.value = LoginUiState.SuccessLogin(it) }
                it.onFailure { _loginUiFlow.value = LoginUiState.FailLogin(it.message) }
            }
            .launchIn(viewModelScope)
    }
}

sealed class LoginUiState {
    object None : LoginUiState()
    object Loading : LoginUiState()
    data class SuccessLogin(val user: User) : LoginUiState()
    data class FailLogin(val msg: String? = "") : LoginUiState()
}