package com.example.data.repos

import com.example.data.api.KtorClient
import com.example.data.dto.UserDto
import com.example.data.dto.toToUser
import com.example.data.thread.ThreadContract
import com.example.domain.contracts.LoginContract
import com.example.domain.models.User
import io.ktor.client.call.*
import io.ktor.client.request.*
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class LoginRepository @Inject constructor(
    ktorClient: KtorClient,
    private val thread: ThreadContract,
) : LoginContract {

    private val httpClient = ktorClient.client

    override fun loginRequest(email: String, password: String): Flow<Result<User>> {
        // This link return 401 Unauthorized
//        val url = "951a55e9-e244-4e6f-b04d-56a5cb0324e1"
        val url = "4a34ade3-8559-4ac8-ba72-dfcc55e693d1"
        return flow {
            val userDto: UserDto = httpClient.get(url).body()
            val user = userDto.toToUser()
            emit(Result.success(user))
        }
            .catch { emit(Result.failure(it)) }
            .flowOn(thread.io)
    }
}
