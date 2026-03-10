package ru.russeb.graduationwork.dto
import java.math.BigDecimal
import java.time.LocalDateTime

data class ProductDto(
    val id: Long,
    val categoryId: Long,
    val categoryName: String? = null,
    val sku: String,
    val name: String,
    val slug: String,
    val description: String?,
    val price: BigDecimal,
    val oldPrice: BigDecimal? = null,
    val quantity: Int,
    val availableQuantity: Int,
    val inStock: Boolean,
    val weight: Long,
    val isActive: Boolean,
    val isFeatured: Boolean,
    val viewsCount: Long,
    val purchasesCount: Long,
    val rating: BigDecimal,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime,
    val images: List<ProductImageDto> = emptyList(),
    val attributes: List<ProductAttributeValueDto> = emptyList(),
    val mainImageUrl: String? = null,
    var parentsCategories: List<CategoryDto>? = null
)