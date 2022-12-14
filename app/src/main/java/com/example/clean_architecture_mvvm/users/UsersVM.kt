package com.example.clean_architecture_mvvm.users

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.clean_architecture_mvvm.ui_state.UiResult
import com.example.clean_architecture_mvvm.ui_state.UiResult.Companion.success
import com.example.clean_architecture_mvvm.ui_state.UiResult.Companion.error
import com.example.domain.usecases.FetchUsersUseCase
import com.example.domain.models.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

typealias UsersFlow = UiResult<List<User>>

@HiltViewModel
class UsersVM @Inject constructor(
    private val usersUseCase: FetchUsersUseCase
) : ViewModel() {

    private val _usersUiFlow = MutableStateFlow<UsersFlow>(UiResult.none())
    val usersUiFlow = _usersUiFlow.asStateFlow()

    fun fetchUsers() {
        val result = usersUseCase.fetchUsers()

        result
            .onStart { _usersUiFlow.value = UiResult.loading() }
            .onEach { response ->
                response.onSuccess { users -> _usersUiFlow.value = success(users) }
                response.onFailure { exception -> _usersUiFlow.value = error(exception.message) }
            }
            .launchIn(viewModelScope)
    }


}