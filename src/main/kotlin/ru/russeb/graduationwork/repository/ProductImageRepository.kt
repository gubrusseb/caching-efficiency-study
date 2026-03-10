package ru.russeb.graduationwork.repository

import org.springframework.data.jpa.repository.JpaRepository
import ru.russeb.graduationwork.entity.ProductImage

interface ProductImageRepository: JpaRepository<ProductImage, Long> {

    fun findByProductIdOrderBySortOrder(productId: Long): List<ProductImage>

    fun findByProductIdAndIsMainTrue(productId: Long): ProductImage?
}