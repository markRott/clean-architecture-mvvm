package com.example.data.api

import com.example.data.exception.ExceptionManager
import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier
import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.serialization.kotlinx.json.*

private const val BASE_URL = "https://run.mocky.io/v3/"

class KtorClient {

    val client: HttpClient = HttpClient(Android) {
        expectSuccess = true
        setupDefaultProperty()
        install(HttpTimeout)
        install(ContentNegotiation) { json() }
        install(Logging) { initLogger() }
        prepareHttpResponseValidator()
    }

    private fun HttpClientConfig<AndroidEngineConfig>.setupDefaultProperty() {
        install(DefaultRequest) { url(BASE_URL) }
    }

    private fun Logging.Config.initLogger() {
        level = LogLevel.BODY
        logger = KtorLogger.getLogger()
            .also { Napier.base(DebugAntilog()) }
    }

    private fun HttpClientConfig<AndroidEngineConfig>.prepareHttpResponseValidator() {
        HttpResponseValidator {
            handleResponseExceptionWithRequest { exception, _ ->
                ExceptionManager.getException(exception)
            }
        }
    }
}