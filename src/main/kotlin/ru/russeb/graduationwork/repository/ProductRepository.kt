package ru.russeb.graduationwork.repository

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.transaction.annotation.Transactional
import ru.russeb.graduationwork.entity.Product
import java.util.Optional

interface ProductRepository: JpaRepository<Product, Long> {

    fun findBySlug(slug: String): Optional<Product>

    fun findByCategoryIdAndIsActiveTrue(categoryId: Long, pageable: Pageable): Page<Product>

    @Modifying
    @Transactional
    @Query("UPDATE Product p SET p.viewsCount = p.viewsCount + 1 WHERE p.id = :id")
    fun incrementViewsCount(@Param("id") id: Long)
}