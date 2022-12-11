package com.example.data.api

import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier
import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.serialization.kotlinx.json.*
import kotlin.concurrent.thread

private const val BASE_URL = "https://httpbin.org"
private const val GET_UUID = "$BASE_URL/uuid"

class KtorClient {

    val client: HttpClient = HttpClient(Android) {

        install(DefaultRequest) {
            url("https://run.mocky.io/v3/")
//            headers.append("Authorization", "Bearer abc123")
        }

        install(HttpTimeout)
        install(ContentNegotiation) { json() }
        install(Logging) { initLogger() }
    }

    private fun Logging.Config.initLogger() {
        level = LogLevel.BODY
        logger = KtorLogger.getLogger()
            .also { Napier.base(DebugAntilog()) }
    }
}