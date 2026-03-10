package ru.russeb.graduationwork.controller

import ru.russeb.graduationwork.service.ProductService
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.data.web.PageableDefault
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import ru.russeb.graduationwork.dto.CategoryDto
import ru.russeb.graduationwork.service.CategoryService

@Controller
@RequestMapping("/product")
class ProductController(
    private val productService: ProductService,
    private val categoryService: CategoryService
) {

    @GetMapping("/{slug}")
    fun productPage(
        @PathVariable slug: String,
        model: Model
    ): String {
        val product = productService.getProductBySlug(slug)

        // Получаем похожие товары из той же категории
        val similarProducts = productService.getProductsByCategory(
            categoryId = product.categoryId,
            pageable = Pageable.ofSize(4)
        ).content

        product.parentsCategories = categoryService.getFullPathCategoryById(product.categoryId)
            .map { category ->
                CategoryDto(
                    name = category.name,
                    slug = category.slug
                )
            }

        model.addAttribute("product", product)
        model.addAttribute("similarProducts", similarProducts)
        model.addAttribute("pageTitle", product.name)
        model.addAttribute("productBreadcrumb", product.name.split("[")[0])

        return "product"
    }

    @GetMapping("/category/{categoryId}")
    fun categoryProducts(
        @PathVariable categoryId: Long,
        @PageableDefault(size = 12, sort = ["createdAt"], direction = Sort.Direction.DESC) pageable: Pageable,
        @RequestParam(required = false) minPrice: Double?,
        @RequestParam(required = false) maxPrice: Double?,
        model: Model
    ): String {
        val products = productService.getProductsByCategory(categoryId, pageable)

        model.addAttribute("products", products)
        model.addAttribute("categoryId", categoryId)
        model.addAttribute("minPrice", minPrice)
        model.addAttribute("maxPrice", maxPrice)

        return "category-products"
    }

    @GetMapping("/search")
    fun searchProducts(
        @RequestParam q: String,
        @PageableDefault(size = 12) pageable: Pageable,
        model: Model
    ): String {
        val results = productService.searchProducts(q, pageable)

        model.addAttribute("products", results)
        model.addAttribute("query", q)
        model.addAttribute("resultsCount", results.totalElements)

        return "search-results"
    }
}