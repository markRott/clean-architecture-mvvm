package com.example.clean_architecture_mvvm.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.clean_architecture_mvvm.ui_state.Status
import com.example.clean_architecture_mvvm.ui_state.UiResult
import com.example.clean_architecture_mvvm.ui_state.UiResult.Companion.success
import com.example.clean_architecture_mvvm.ui_state.UiResult.Companion.error
import com.example.domain.LoginUseCase
import com.example.domain.models.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class LoginVM @Inject constructor(private val loginUseCase: LoginUseCase) : ViewModel() {

    private val _loginUiFlow = MutableStateFlow<UiResult<User>>(UiResult.none())
    val loginFlow = _loginUiFlow
        .asStateFlow()
        .filter { it.status != Status.NONE }

    fun loginRequest(email: String, password: String) {
        val result = loginUseCase.executeLoginRequest(email, password)
        result
            .onStart { _loginUiFlow.value = UiResult.loading() }
            .onEach {
                it.onSuccess { _loginUiFlow.value = success(it) }
                it.onFailure { _loginUiFlow.value = error(it.message) }
            }
            .launchIn(viewModelScope)
    }
}