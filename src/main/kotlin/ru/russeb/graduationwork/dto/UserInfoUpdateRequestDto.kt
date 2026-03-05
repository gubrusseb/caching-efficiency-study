package ru.russeb.graduationwork.dto

data class UserInfoUpdateRequestDto
(
    val fullName: String = "",
    val email: String = "",
    val phone: String = ""
)