package com.example.domain.repository

import com.example.domain.models.User
import kotlinx.coroutines.flow.Flow

interface UsersRepoContract {

    fun fetchUsersRequest(): Flow<Result<List<User>>>
}