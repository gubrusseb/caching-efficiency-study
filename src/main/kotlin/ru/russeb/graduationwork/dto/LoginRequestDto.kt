package ru.russeb.graduationwork.dto


data class LoginRequestDto(

    val email: String = "",
    val password: String = "",
    val isRemember: Boolean = false
)