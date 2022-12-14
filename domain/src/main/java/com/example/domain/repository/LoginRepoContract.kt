package com.example.domain.repository

import com.example.domain.models.User
import kotlinx.coroutines.flow.Flow

interface LoginRepoContract {

    fun loginRequest(email: String, password: String): Flow<Result<User>>
}