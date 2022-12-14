package com.example.domain.repository

import com.example.domain.models.User
import kotlinx.coroutines.flow.Flow

interface UsersContract {

    fun fetchUsersRequest(): Flow<Result<List<User>>>
}