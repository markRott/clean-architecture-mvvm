package com.example.clean_architecture_mvvm.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginVM @Inject constructor(private val loginUseCase: LoginUseCase) : ViewModel() {

    private val _loginUiFlow = MutableStateFlow<LoginUiState>(LoginUiState.Loading)
    val loginFlow = _loginUiFlow.asStateFlow()

    fun executeLogin(email: String, password: String) {
        viewModelScope.launch {
            loginUseCase.executeLoginRequest().collect {
                println("Response: $it")
            }
        }
    }
}

sealed class LoginUiState {
    object Loading : LoginUiState()
    object SuccessLogin : LoginUiState()
    object FailLogin : LoginUiState()
}