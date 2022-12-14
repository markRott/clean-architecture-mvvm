package com.example.domain.usecases

import com.example.domain.repository.UsersContract
import com.example.domain.models.User
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FetchUsersUseCase @Inject constructor(
    private val usersContract: UsersContract
) {

    fun fetchUsers(): Flow<Result<List<User>>> {
        return usersContract.fetchUsersRequest()
    }
}