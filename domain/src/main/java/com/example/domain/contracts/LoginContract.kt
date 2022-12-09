package com.example.domain.contracts

interface LoginContract {

    fun executeLogin(email: String, password: String)
}