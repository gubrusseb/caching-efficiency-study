package ru.russeb.graduationwork.dto


data class LoginRequest(

    val email: String = "",
    val password: String = "",
    val isRemember: Boolean = false
)