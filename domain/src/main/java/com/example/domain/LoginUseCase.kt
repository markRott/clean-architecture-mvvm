package com.example.domain

import com.example.domain.contracts.LoginContract
import com.example.domain.models.User
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val loginContract: LoginContract
) {

    fun executeLoginRequest(): Flow<User> {
        return loginContract.executeLoginRequest()
    }
}