package ru.russeb.graduationwork.dto

import jakarta.validation.constraints.*

data class UserCreateRequest(
    @field:NotBlank(message = "Обязательное поле")
    @field:Email(message = "Неверный формат почты")
    val email: String,

    @field:NotBlank(message = "Обязательное поле")
    @field:Size(min = 8, message = "Минимальный размер пароля - 8 символов")
    val password: String,

    @field:NotBlank(message = "Обязательное поле")
    @field:Size(max = 200, message = "Полное имя не может быть длиннее 200 символов")
    val fullName: String,

    @field:NotBlank(message = "Обязательное поле")
    @field:Pattern(regexp = "^\\+?[0-9]{10,15}$", message = "Неверный формат телефона")
    val phone: String,

    @field:NotBlank(message = "Обязательное поле")
    val confirmPassword: String,

    @field:AssertTrue(message = "Необходимо принять условия использования")
    val terms: Boolean
){
    constructor() : this("", "", "", "", "", false)
}