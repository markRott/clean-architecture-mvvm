package com.example.data.mappers

import com.example.data.models.db.UserDb
import com.example.data.models.dto.UserDto
import com.example.domain.models.User

fun UserDto.toUser(): User {
    return User(userId, emailAddress, firstName, lastName, phoneNumber)
}

fun UserDto.toUserDb() : UserDb {
    return UserDb(
        userId,
        emailAddress ?: "",
        firstName ?: "",
        lastName ?: "",
        phoneNumber ?: ""
    )
}

fun UserDb.toUser(): User {
    return User(userId, emailAddress, firstName, lastName, phoneNumber)
}