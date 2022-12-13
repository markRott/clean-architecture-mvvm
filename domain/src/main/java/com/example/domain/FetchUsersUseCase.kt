package com.example.domain

import com.example.domain.contracts.UsersContract
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