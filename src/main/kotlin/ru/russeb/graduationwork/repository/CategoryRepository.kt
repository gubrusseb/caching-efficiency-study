package ru.russeb.graduationwork.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import ru.russeb.graduationwork.entity.Category

@Repository
interface CategoryRepository: JpaRepository<Category, Long> {

    @Query("""
        SELECT DISTINCT c 
        FROM Category c
        WHERE c.parent IS NULL
        AND c.isActive IS true
        ORDER BY c.sortOrder
    """)
    fun findRootCategoriesWithChildren(): List<Category>

    fun findCategoryBySlug(slug: String): Category?

    fun findCategoriesByParent(parent: Category): List<Category>

}