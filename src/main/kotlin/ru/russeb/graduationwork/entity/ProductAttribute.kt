package ru.russeb.graduationwork.entity
import jakarta.persistence.*

@Entity
@Table(name = "product_attributes")
data class ProductAttribute(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(nullable = false)
    var name: String,

    var type: String = "string"
)