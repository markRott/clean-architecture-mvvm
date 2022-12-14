package com.example.data.models.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UserDb(
    @PrimaryKey val userId: Int,
    val emailAddress: String = "",
    val firstName: String = "",
    val lastName: String = "",
    val phoneNumber: String = ""
)