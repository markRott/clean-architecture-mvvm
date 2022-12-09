package com.example.domain

import com.example.domain.contracts.LoginContract
import javax.inject.Inject

class LoginUseCase @Inject constructor(private val loginContract: LoginContract) {

    init {
        println("markrott Init LoginUseCase")
    }

    fun executeLogin(email: String, password: String) {
        loginContract.executeLogin(email, password)
    }
}