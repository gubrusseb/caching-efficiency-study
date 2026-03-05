package ru.russeb.graduationwork.dto

import jakarta.validation.constraints.NotBlank

data class AddressCreateRequestDto(
    @field:NotBlank(message = "Улица и дом обязательны")
    val line1: String,

    val line2: String? = null,

    @field:NotBlank(message = "Город обязателен")
    val city: String,

    val postalCode: String? = null,

    val country: String = "Россия",

    val isDefault: Boolean = false
)