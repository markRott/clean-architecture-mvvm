package com.example.data.repository

import com.example.data.api.KtorClient
import com.example.data.database.DbInitializer
import com.example.data.models.db.UserDb
import com.example.data.models.db.toUser
import com.example.data.models.dto.UserDto
import com.example.data.models.dto.toUser
import com.example.data.models.dto.toUserDb
import com.example.data.thread.ThreadContract
import com.example.domain.repository.UsersContract
import com.example.domain.models.User
import io.ktor.client.call.*
import io.ktor.client.request.*
import kotlinx.coroutines.flow.*
import javax.inject.Inject

typealias Users = Result<List<User>>

class UsersRepository @Inject constructor(
    ktorClient: KtorClient,
    dbInitializer: DbInitializer,
    private val thread: ThreadContract
) : UsersContract {

    private val httpClient = ktorClient.client
    private val userDao = dbInitializer.database.userDao()

    override fun fetchUsersRequest(): Flow<Users> {
        return flow {
            val dbUsers: List<UserDb> = userDao.getUsers()
            when {
                dbUsers.isNotEmpty() -> fetchUsersFromDb(dbUsers)
                else -> fetchUsersFromServer()
            }
        }
            .catch { emit(Result.failure(it)) }
            .flowOn(thread.io)
    }

    private suspend fun FlowCollector<Users>.fetchUsersFromDb(dbUsers: List<UserDb>) {
        val users = dbUsers.map { it.toUser() }
        emit(Result.success(users))
    }

    private suspend fun FlowCollector<Users>.fetchUsersFromServer() {
        val url = "ece2bdff-fbc2-4bf3-92b5-9d0efa18cc78"
        val usersDto: List<UserDto> = httpClient.get(url).body()
        val users = usersDto.map { it.toUser() }
        saveUsers(usersDto)

        emit(Result.success(users))
    }

    private fun saveUsers(usersDto: List<UserDto>) {
        val dbUsers: List<UserDb> = usersDto.map { it.toUserDb() }
        userDao.saveUsers(dbUsers)
    }
}