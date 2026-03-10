package ru.russeb.graduationwork.service

import org.springframework.stereotype.Service
import ru.russeb.graduationwork.dto.CategoryDto
import ru.russeb.graduationwork.entity.Category
import ru.russeb.graduationwork.repository.CategoryRepository

@Service
class CategoryService(private val categoryRepository: CategoryRepository) {


    fun getRootCategoriesAsDto(): List<CategoryDto> {
        val result = ArrayList<CategoryDto>()
        val categories = categoryRepository.findRootCategoriesWithChildren()
        for (category in categories) {
            result.add(CategoryDto(category.name,category.slug,category.description,category.imageUrl))
        }
        return result
    }

    fun getCategoryBySlug(slug: String): Category? {
        return categoryRepository.findCategoryBySlug(slug)
    }

    fun getSubCategories(category: Category): List<Category> {
        return categoryRepository.findCategoriesByParent(category)
    }

    fun getFullPathCategoryById(categoryId: Long): List<Category> {
        val path = mutableListOf<Category>()
        var current = categoryRepository.findById(categoryId).orElse(null)

        while (current != null) {
            path.add(0, current)
            current = current.parent
        }

        return path
    }
}