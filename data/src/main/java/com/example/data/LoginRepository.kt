package com.example.data

import com.example.domain.contracts.LoginContract
import javax.inject.Inject

class LoginRepository @Inject constructor() : LoginContract {

    init {
        println("markrott Init LoginRepository")
    }

    override fun executeLogin(email: String, password: String) {
        println("markrott executeLogin: $email and $password")
    }
}