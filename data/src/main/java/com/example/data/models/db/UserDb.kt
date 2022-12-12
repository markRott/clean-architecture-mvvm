package com.example.data.models.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.domain.models.User

@Entity
data class UserDb(
    @PrimaryKey val userId: Int,
    val emailAddress: String = "",
    val firstName: String = "",
    val lastName: String = "",
    val phoneNumber: String = ""
)

fun UserDb.toUser(): User {
    return User(userId, emailAddress, firstName, lastName, phoneNumber)
}