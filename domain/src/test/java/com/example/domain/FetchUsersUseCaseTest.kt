package com.example.domain

import com.example.domain.repository.UsersContract
import com.example.domain.models.User
import com.example.domain.usecases.FetchUsersUseCase
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import org.junit.Assert.*
import org.junit.Before

import org.junit.Test
import strikt.api.expectThat
import strikt.assertions.isEqualTo

@OptIn(ExperimentalCoroutinesApi::class)
class FetchUsersUseCaseTest {

    private lateinit var fetchUsersUseCase: FetchUsersUseCase
    @MockK private lateinit var usersContract: UsersContract

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        fetchUsersUseCase = FetchUsersUseCase(usersContract)
    }

    @Test
    fun `success fetch users`() {

        val expectedUsers: List<User> = listOf(
            getExpectedUser(),
            getExpectedUser(),
            getExpectedUser()
        )

        val expectedUsersFlow: Flow<Result<List<User>>> = flowOf(Result.success(expectedUsers))
        coEvery { usersContract.fetchUsersRequest() } returns expectedUsersFlow

        val actualResult: Flow<Result<List<User>>> = fetchUsersUseCase.fetchUsers()
        expectThat(actualResult).isEqualTo(expectedUsersFlow)
    }

    private fun getExpectedUser(): User {
        return User(
            1,
            "markrott@testemail.com",
            "Mark",
            "Rott",
            "+1(505)45-64-987)"
        )
    }
}