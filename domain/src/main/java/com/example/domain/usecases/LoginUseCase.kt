package com.example.domain.usecases

import com.example.domain.repository.LoginContract
import com.example.domain.models.User
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val loginContract: LoginContract
) {

    fun executeLoginRequest(email: String, password: String): Flow<Result<User>> {
        return loginContract.loginRequest(email, password)
    }
}