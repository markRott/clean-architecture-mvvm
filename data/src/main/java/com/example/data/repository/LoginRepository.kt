package com.example.data.repository

import com.example.data.api.KtorClient
import com.example.data.mappers.toUser
import com.example.data.models.dto.UserDto
import com.example.data.thread.ThreadContract
import com.example.domain.repository.LoginRepoContract
import com.example.domain.models.User
import io.ktor.client.call.*
import io.ktor.client.request.*
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class LoginRepository @Inject constructor(
    ktorClient: KtorClient,
    private val thread: ThreadContract,
) : LoginRepoContract {

    private val httpClient = ktorClient.client

    // This link return 401 Unauthorized
    // val url = "951a55e9-e244-4e6f-b04d-56a5cb0324e1"

    override fun loginRequest(email: String, password: String): Flow<Result<User>> {
        val url = "4a34ade3-8559-4ac8-ba72-dfcc55e693d1"

        return flow {
            val userDto: UserDto = httpClient.get(url).body()
            val user = userDto.toUser()
            emit(Result.success(user))
        }
            .catch { emit(Result.failure(it)) }
            .flowOn(thread.io)
    }
}
