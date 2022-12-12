package com.example.clean_architecture_mvvm.users

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.GetUsersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.aakira.napier.Napier
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

@HiltViewModel
class UsersVM @Inject constructor(
    private val usersUseCase: GetUsersUseCase
) : ViewModel() {

    fun fetchUsers() {
        val result = usersUseCase.fetchUsers()
        result
            .onStart { }
            .onEach {
                it.onSuccess {
                    Napier.i("Users: $it")
                }
                it.onFailure {
                    Napier.e("Users exception: $it")
                }
            }
            .launchIn(viewModelScope)
    }
}