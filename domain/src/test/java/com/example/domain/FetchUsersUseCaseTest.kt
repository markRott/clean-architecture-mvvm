package com.example.domain

import com.example.domain.repository.UsersRepoContract
import com.example.domain.models.User
import com.example.domain.usecases.FetchUsersUseCase
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import org.junit.Before

import org.junit.Test
import strikt.api.expectThat
import strikt.assertions.isEqualTo

class FetchUsersUseCaseTest {

    private lateinit var fetchUsersUseCase: FetchUsersUseCase
    @MockK private lateinit var usersRepoContract: UsersRepoContract

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        fetchUsersUseCase = FetchUsersUseCase(usersRepoContract)
    }

    @Test
    fun `success fetch users`() {

        val expectedUsers: List<User> = listOf(
            getExpectedUser(),
            getExpectedUser(),
            getExpectedUser()
        )

        val expectedUsersFlow: Flow<Result<List<User>>> = flowOf(Result.success(expectedUsers))
        coEvery { usersRepoContract.fetchUsersRequest() } returns expectedUsersFlow

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