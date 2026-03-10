package ru.russeb.graduationwork.entity

import jakarta.persistence.*

@Entity
@Table(name = "product_attribute_link")
data class ProductAttributeLink(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @ManyToOne
    @JoinColumn(name = "product_id")
    var product: Product,

    @ManyToOne
    @JoinColumn(name = "attribute_id")
    var attribute: ProductAttribute,

    @Column(columnDefinition = "TEXT")
    var value: String
)