package ru.russeb.graduationwork.dto

data class ProductImageDto(
    val id: Long,
    val imageUrl: String,
    val sortOrder: Int,
    val isMain: Boolean
)