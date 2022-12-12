package com.example.data.api

import io.github.aakira.napier.Napier
import io.ktor.client.plugins.logging.*

object KtorLogger {

    fun getLogger(): Logger {
        return object : Logger {
            override fun log(message: String) {
                Napier.i(tag = "MyApp", throwable = null, message = message)
            }
        }
    }
}