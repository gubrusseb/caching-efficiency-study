package ru.russeb.graduationwork.dto

data class UserPasswordUpdateRequestDto(
    val currentPassword: String = "",
    val newPassword: String = ""
)