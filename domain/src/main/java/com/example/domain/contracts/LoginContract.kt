package com.example.domain.contracts

import com.example.domain.models.User
import kotlinx.coroutines.flow.Flow

interface LoginContract {

    suspend fun executeLogin(email: String, password: String)

    fun executeLoginRequest() : Flow<User>
}