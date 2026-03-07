package ru.russeb.graduationwork.controller

import jakarta.servlet.http.HttpServletRequest
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import ru.russeb.graduationwork.dto.CategoryDto
import ru.russeb.graduationwork.entity.Category
import ru.russeb.graduationwork.service.CategoryService

@Controller
class CategoryController(private val categoryService: CategoryService) {

    @GetMapping("/catalog")
    fun getRootCatalog(model: Model): String {
        val rootCategories = categoryService.getRootCategoriesAsDto()
        model.addAttribute("categories", rootCategories)
        return "catalog"
    }

    @GetMapping("/catalog/{*categoryPath}")
    fun getCategoryByPath(
        model: Model,
        @PathVariable categoryPath: String,
        request: HttpServletRequest
    ): String {
        println("Requested path: $categoryPath")
        val slugs = categoryPath.split("/")
        val targetSlug = slugs.last()

        val category = categoryService.getCategoryBySlug(targetSlug)

        val subCategories = categoryService.getSubCategories(category!!)
        val subCategoriesDto = ArrayList<CategoryDto>()
        for (subCategory in subCategories) {
            subCategoriesDto.add(CategoryDto( subCategory.name,subCategory.slug,subCategory.description,null,null))
        }

        val parentsName = buildParentsCategoriesDto(category)
        val categoryDto = CategoryDto(category.name, category.slug, category.description, null,parentsName)


        model.addAttribute("subCategories", subCategoriesDto)

        model.addAttribute("category", categoryDto)

        val expectedPath = buildPath(category)
        if(categoryPath != expectedPath) {
            return "redirect:/catalog$expectedPath"
        }
        model.addAttribute("currentUri", request.requestURI)
        return "subcatalog"
    }

    private fun buildPath(category: Category): String {
        val slugs = mutableListOf<String>()
        var current: Category? = category

        while (current != null) {
            slugs.add(0, current.slug ?: "")
            current = current.parent
        }

        return "/"+slugs.joinToString("/")
    }

    private fun buildParentsCategoriesDto(category: Category): List<CategoryDto> {
        val slugs = mutableListOf<CategoryDto>()
        var current: Category? = category

        while (current != null) {
            slugs.add(0, CategoryDto(current.name,current.slug,null,null,null))
            current = current.parent
        }

        return slugs
    }
}