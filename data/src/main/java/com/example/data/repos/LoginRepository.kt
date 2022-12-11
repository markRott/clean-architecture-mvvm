package com.example.data.repos

import com.example.data.api.KtorClient
import com.example.data.dto.UserDto
import com.example.data.dto.userDtoToUser
import com.example.data.thread.ThreadContract
import com.example.domain.contracts.LoginContract
import com.example.domain.models.User
import io.ktor.client.call.*
import io.ktor.client.request.*
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class LoginRepository @Inject constructor(
    private val ktorClient: KtorClient,
    private val thread: ThreadContract,
) : LoginContract {

    override suspend fun executeLogin(email: String, password: String) {}

    override fun executeLoginRequest(): Flow<User> {
        return flow {
            val url = "4a34ade3-8559-4ac8-ba72-dfcc55e693d1"
            val httpClient = ktorClient.client
            val loginDto: UserDto = httpClient.get(url).body()
            emit(loginDto.userDtoToUser())
            println("Thread: ${Thread.currentThread().name}")
        }.flowOn(thread.io)
    }
}
