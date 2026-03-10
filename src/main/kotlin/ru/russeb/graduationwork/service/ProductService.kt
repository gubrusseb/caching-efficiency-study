package ru.russeb.graduationwork.service

import ru.russeb.graduationwork.dto.ProductCardDto
import ru.russeb.graduationwork.dto.ProductDto
import ru.russeb.graduationwork.exception.ProductNotFoundException
import ru.russeb.graduationwork.mapper.ProductMapper
import ru.russeb.graduationwork.repository.*
import org.springframework.cache.annotation.CacheEvict
import org.springframework.cache.annotation.Cacheable
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.math.BigDecimal

@Service
class ProductService(
    private val productRepository: ProductRepository,
    private val categoryRepository: CategoryRepository,
    private val productImageRepository: ProductImageRepository,
    private val productAttributeLinkRepository: ProductAttributeLinkRepository,
    private val productAttributeRepository: ProductAttributeRepository,
    private val mapper: ProductMapper
) {

    @Cacheable(value = ["product"], key = "#slug")
    @Transactional()
    fun getProductBySlug(slug: String): ProductDto {
        val product = productRepository.findBySlug(slug)
            .orElseThrow { ProductNotFoundException("Товар с slug $slug не найден") }

        // Асинхронно увеличиваем счетчик просмотров
        incrementViewsCountAsync(product.id)

        // Загружаем изображения
        val images = productImageRepository.findByProductIdOrderBySortOrder(product.id)
        product.images.addAll(images)

        // Загружаем атрибуты
        val attributes = productAttributeLinkRepository.findByProductIdWithAttribute(product.id)
        product.attributeValues.addAll(attributes)

        // Получаем название категории
        val category = categoryRepository.findById(product.categoryId).orElse(null)
        val categoryName = category?.name

        return mapper.toDto(product, categoryName)
    }

    @Cacheable(value = ["product_cards"], key = "#categoryId + '_' + #pageable.pageNumber")
    @Transactional(readOnly = true)
    fun getProductsByCategory(categoryId: Long, pageable: Pageable): Page<ProductCardDto> {
        val products = productRepository.findByCategoryIdAndIsActiveTrue(categoryId, pageable)

        val productCards = products.content.map { product ->
            val mainImage = productImageRepository.findByProductIdAndIsMainTrue(product.id)
            mapper.toCardDto(product, mainImage?.imageUrl)
        }

        return PageImpl(productCards, pageable, products.totalElements)
    }

//    @Cacheable(value = ["featured_products"])
//    @Transactional(readOnly = true)
//    fun getFeaturedProducts(limit: Int = 8): List<ProductCardDto> {
//        val products = productRepository.findFeaturedProducts(Pageable.ofSize(limit))
//
//        return products.map { product ->
//            val mainImage = productImageRepository.findByProductIdAndIsMainTrue(product.id)
//            mapper.toCardDto(product, mainImage?.imageUrl)
//        }
//    }

//    @Cacheable(value = ["top_selling"])
//    @Transactional(readOnly = true)
//    fun getTopSellingProducts(limit: Int = 10): List<ProductCardDto> {
//        val products = productRepository.findTopSelling(Pageable.ofSize(limit))
//
//        return products.map { product ->
//            val mainImage = productImageRepository.findByProductIdAndIsMainTrue(product.id)
//            mapper.toCardDto(product, mainImage?.imageUrl)
//        }
//    }

    @CacheEvict(value = ["product", "product_cards", "featured_products", "top_selling"], allEntries = true)
    @Transactional
    fun updateProduct(productDto: ProductDto): ProductDto {
        val product = productRepository.findById(productDto.id)
            .orElseThrow { ProductNotFoundException("Товар с ID ${productDto.id} не найден") }

        // Обновляем поля
        product.name = productDto.name
        product.price = productDto.price
        product.description = productDto.description
        product.quantity = productDto.quantity
        product.isActive = productDto.isActive
        product.isFeatured = productDto.isFeatured
        product.updatedAt = java.time.LocalDateTime.now()

        val savedProduct = productRepository.save(product)
        return mapper.toDto(savedProduct)
    }

    @CacheEvict(value = ["product"], key = "#slug")
    @Transactional
    fun incrementViewsCount(slug: String) {
        val product = productRepository.findBySlug(slug)
            .orElseThrow { ProductNotFoundException("Товар с slug $slug не найден") }
        product.viewsCount++
        productRepository.save(product)
    }

    private fun incrementViewsCountAsync(productId: Long) {
        // Можно реализовать через @Async или CompletableFuture
        productRepository.incrementViewsCount(productId)
    }

    @Transactional
    fun updateProductRating(productId: Long, newRating: BigDecimal) {
        val product = productRepository.findById(productId)
            .orElseThrow { ProductNotFoundException("Товар с ID $productId не найден") }
        product.rating = newRating
        productRepository.save(product)
    }

    // Поиск товаров
    fun searchProducts(query: String, pageable: Pageable): Page<ProductCardDto> {
        // Реализация поиска (можно через JPA или полнотекстовый)
        TODO("Реализовать поиск")
    }

    // Фильтрация товаров
    fun filterProducts(
        categoryId: Long,
        minPrice: BigDecimal? = null,
        maxPrice: BigDecimal? = null,
        attributes: Map<Long, List<String>> = emptyMap(),
        pageable: Pageable
    ): Page<ProductCardDto> {
        // Реализация фильтрации
        TODO("Реализовать фильтрацию")
    }
}
