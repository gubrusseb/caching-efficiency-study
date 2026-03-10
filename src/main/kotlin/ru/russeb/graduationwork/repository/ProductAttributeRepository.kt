package ru.russeb.graduationwork.repository

import org.springframework.data.jpa.repository.JpaRepository
import ru.russeb.graduationwork.entity.ProductAttribute

interface ProductAttributeRepository: JpaRepository<ProductAttribute, Long> {
}