package ru.russeb.graduationwork.mapper


import ru.russeb.graduationwork.dto.*
import ru.russeb.graduationwork.entity.*
import org.springframework.stereotype.Component

@Component
class ProductMapper {

    fun toDto(product: Product, categoryName: String? = null): ProductDto {
        val images = product.images.map { toImageDto(it) }
        val attributes = product.attributeValues.map { toAttributeValueDto(it) }

        return ProductDto(
            id = product.id,
            categoryId = product.categoryId,
            categoryName = categoryName,
            sku = product.sku,
            name = product.name,
            slug = product.slug,
            description = product.description,
            price = product.price,
            quantity = product.quantity,
            availableQuantity = product.availableQuantity,
            inStock = product.inStock,
            weight = product.weight,
            isActive = product.isActive,
            isFeatured = product.isFeatured,
            viewsCount = product.viewsCount,
            purchasesCount = product.purchasesCount,
            rating = product.rating,
            createdAt = product.createdAt,
            updatedAt = product.updatedAt,
            images = images,
            attributes = attributes,
            mainImageUrl = images.find { it.isMain }?.imageUrl ?: images.firstOrNull()?.imageUrl
        )
    }

    fun toImageDto(image: ProductImage): ProductImageDto {
        return ProductImageDto(
            id = image.id,
            imageUrl = image.imageUrl,
            sortOrder = image.sortOrder,
            isMain = image.isMain
        )
    }

    fun toAttributeValueDto(link: ProductAttributeLink): ProductAttributeValueDto {
        return ProductAttributeValueDto(
            attributeId = link.attribute.id,
            attributeName = link.attribute.name,
            attributeType = link.attribute.type,
            value = link.value
        )
    }

    fun toCardDto(product: Product, mainImageUrl: String? = null): ProductCardDto {
        return ProductCardDto(
            id = product.id,
            name = product.name,
            slug = product.slug,
            price = product.price,
            rating = product.rating,
            inStock = product.inStock,
            mainImageUrl = mainImageUrl
        )
    }
}
