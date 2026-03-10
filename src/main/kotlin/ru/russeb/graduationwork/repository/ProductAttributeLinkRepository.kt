package ru.russeb.graduationwork.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import ru.russeb.graduationwork.entity.ProductAttributeLink

interface ProductAttributeLinkRepository: JpaRepository<ProductAttributeLink, Long> {

    @Query("SELECT pal FROM ProductAttributeLink pal JOIN FETCH pal.attribute WHERE pal.product.id = :productId")
    fun findByProductIdWithAttribute(@Param("productId") productId: Long): List<ProductAttributeLink>
}