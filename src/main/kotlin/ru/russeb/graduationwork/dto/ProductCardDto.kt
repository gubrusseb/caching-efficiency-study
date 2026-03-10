package ru.russeb.graduationwork.dto

import java.math.BigDecimal

data class ProductCardDto(
    val id: Long,
    val name: String,
    val slug: String,
    val price: BigDecimal,
    val oldPrice: BigDecimal? = null,
    val rating: BigDecimal,
    val inStock: Boolean,
    val mainImageUrl: String? = null
)