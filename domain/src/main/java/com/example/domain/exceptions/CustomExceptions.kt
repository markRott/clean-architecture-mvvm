package com.example.domain.exceptions

class UnauthorizedExceptions(text: String) : Throwable(text)

class ServerError(text: String) : Throwable(text)