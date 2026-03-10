package ru.russeb.graduationwork.entity
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "product_images")
data class ProductImage(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @ManyToOne
    @JoinColumn(name = "product_id")
    val product: Product,

    @Column(name = "image_url")
    var imageUrl: String,

    @Column(name = "sort_order")
    var sortOrder: Int = 0,

    @Column(name = "is_main")
    var isMain: Boolean = false,

    @Column(name = "created_at")
    var createdAt: LocalDateTime = LocalDateTime.now()
)
