package com.example.data.exception

import com.example.domain.exceptions.ServerError
import com.example.domain.exceptions.UnauthorizedExceptions
import io.ktor.client.plugins.*
import io.ktor.http.HttpStatusCode.Companion.InternalServerError
import io.ktor.http.HttpStatusCode.Companion.Unauthorized

object ExceptionManager {

    fun getException(exception: Throwable) {
        val clientException =
            exception as? ClientRequestException
                ?: exception as? ServerResponseException
                ?: return

        val exceptionResponse = clientException.response

        when (exceptionResponse.status) {
            Unauthorized -> throw UnauthorizedExceptions("401 Unauthorized")
            InternalServerError -> throw ServerError("Internal Server Error")
        }
    }
}