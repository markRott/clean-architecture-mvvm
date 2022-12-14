package com.example.domain.usecases

import com.example.domain.repository.UsersRepoContract
import com.example.domain.models.User
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FetchUsersUseCase @Inject constructor(
    private val usersRepoContract: UsersRepoContract
) {

    fun fetchUsers(): Flow<Result<List<User>>> {
        return usersRepoContract.fetchUsersRequest()
    }
}