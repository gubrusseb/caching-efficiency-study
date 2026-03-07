package ru.russeb.graduationwork.dto


data class CategoryDto(
    var name: String? = null,
    var slug: String? = null,
    var description: String? = null,
    var imageUrl: String? = null,
    var parentsCategories: List<CategoryDto>? = null
)