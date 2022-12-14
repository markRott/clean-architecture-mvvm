package com.example.domain.usecases

import com.example.domain.repository.LoginRepoContract
import com.example.domain.models.User
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val loginRepoContract: LoginRepoContract
) {

    fun executeLoginRequest(email: String, password: String): Flow<Result<User>> {
        return loginRepoContract.loginRequest(email, password)
    }
}