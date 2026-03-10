package ru.russeb.graduationwork.dto

data class ProductAttributeValueDto(
    val attributeId: Long,
    val attributeName: String,
    val attributeType: String,
    val value: String
)