package com.example.data.dto


import com.example.domain.models.User
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserDto(
    @SerialName("userId")
    val userId: Int?,
    @SerialName("emailAddress")
    val emailAddress: String?,
    @SerialName("firstName")
    val firstName: String?,
    @SerialName("lastName")
    val lastName: String?,
    @SerialName("phoneNumber")
    val phoneNumber: String?
)

fun UserDto.toToUser(): User {
    return User(userId, emailAddress, firstName, lastName, phoneNumber)
}