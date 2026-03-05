package ru.russeb.graduationwork.dto

import jakarta.validation.constraints.NotBlank
import ru.russeb.graduationwork.entity.Address
import ru.russeb.graduationwork.entity.User

data class AddressDto(
    val id: Long? = null,

    @field:NotBlank(message = "Улица и дом обязательны")
    val line1: String,

    val line2: String? = null,

    @field:NotBlank(message = "Город обязателен")
    val city: String,

    val postalCode: String? = null,

    val country: String = "Россия",

    val isDefault: Boolean = false
){
    companion object {
        fun from(address: Address): AddressDto = AddressDto(
            id = address.id!!,
            line1 = address.line1,
            line2 = address.line2,
            city = address.city,
            postalCode = address.postalCode,
            country = address.country,
            isDefault = address.isDefault
        )
    }
}