package com.example.domain.contracts

import com.example.domain.models.User
import kotlinx.coroutines.flow.Flow

interface LoginContract {

    fun loginRequest(
        email: String,
        password: String
    ): Flow<Result<User>>
}