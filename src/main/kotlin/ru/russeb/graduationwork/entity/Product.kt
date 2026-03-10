package ru.russeb.graduationwork.entity

import jakarta.persistence.*
import java.math.BigDecimal
import java.time.LocalDateTime

@Entity
@Table(name = "products")
data class Product(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(name = "category_id")
    var categoryId: Long,

    @Column(unique = true, nullable = false)
    var sku: String,

    @Column(nullable = false)
    var name: String,

    @Column(unique = true, nullable = false)
    var slug: String,

    @Column(columnDefinition = "TEXT")
    var description: String? = null,

    @Column(nullable = false, precision = 10, scale = 2)
    var price: BigDecimal,

    @Column(name = "cost_price", precision = 10, scale = 2)
    var costPrice: BigDecimal? = null,

    var quantity: Int = 0,

    @Column(name = "reserved_quantity")
    var reservedQuantity: Int = 0,

    var weight: Long,

    @Column(name = "is_active")
    var isActive: Boolean = true,

    @Column(name = "is_featured")
    var isFeatured: Boolean = false,

    @Column(name = "views_count")
    var viewsCount: Long = 0,

    @Column(name = "purchases_count")
    var purchasesCount: Long = 0,

    @Column(precision = 3, scale = 2)
    var rating: BigDecimal,

    @Column(name = "created_at")
    var createdAt: LocalDateTime = LocalDateTime.now(),

    @Column(name = "updated_at")
    var updatedAt: LocalDateTime = LocalDateTime.now(),

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
    val images: MutableList<ProductImage> = mutableListOf(),

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
    val attributeValues: MutableList<ProductAttributeLink> = mutableListOf()
) {
    // Вычисляемое поле - доступное количество
    val availableQuantity: Int
        get() = quantity - reservedQuantity

    // Проверка наличия
    val inStock: Boolean
        get() = availableQuantity > 0
}