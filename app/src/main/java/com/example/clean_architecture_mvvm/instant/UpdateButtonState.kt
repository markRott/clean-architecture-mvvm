package com.example.clean_architecture_mvvm.instant

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine

class UpdateButtonState : ViewModel() {

    private val _email = MutableStateFlow("")
    private val _password = MutableStateFlow("")

    fun setEmail(email: String) { _email.value = email }
    fun setPassword(password: String) { _password.value = password }

    val updateButtonFlow: Flow<Boolean> =
        combine(_email, _password) { email, password ->
            return@combine email.isNotEmpty() && password.isNotEmpty()
        }
}