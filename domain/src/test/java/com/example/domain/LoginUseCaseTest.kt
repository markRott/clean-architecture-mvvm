package com.example.domain

import com.example.domain.contracts.LoginContract
import com.example.domain.models.User
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before

import org.junit.Test
import strikt.api.expectThat
import strikt.assertions.isEqualTo

@OptIn(ExperimentalCoroutinesApi::class)
class LoginUseCaseTest {

    private lateinit var loginUseCase: LoginUseCase
    @MockK private lateinit var loginContract: LoginContract

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        loginUseCase = LoginUseCase(loginContract)
    }

    @Test
    fun `execute success login request`() = runTest {
        val expectedUser = getExpectedUser()
        val expectedResult: Flow<Result<User>> = flowOf(Result.success(expectedUser))
        coEvery { loginContract.loginRequest("", "") } returns expectedResult

        val actualResult = loginUseCase.executeLoginRequest("", "")
        expectThat(actualResult).isEqualTo(expectedResult)
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