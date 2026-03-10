package ru.russeb.graduationwork.entity

import jakarta.persistence.*
import java.time.LocalDateTime


@Entity
@Table(name = "addresses")
data class Address(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    var user: User,

    @Column(name = "line1", nullable = false)
    var line1: String,

    @Column(name = "line2")
    var line2: String? = null,

    @Column(name = "city", nullable = false)
    var city: String,

    @Column(name = "postal_code")
    var postalCode: String? = null,

    @Column(name = "country", nullable = false)
    var country: String = "Россия",

    @Column(name = "is_default", nullable = false)
    var isDefault: Boolean = false,

    @Column(name = "created_at")
    val createdAt: LocalDateTime = LocalDateTime.now()
)