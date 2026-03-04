package ru.russeb.graduationwork.entity

import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.LocalDateTime


@Entity
@Table(name = "users")
data class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(nullable = false, unique = true)
    var email: String,

    @Column(name = "password_hash", nullable = false)
    var passwordHash: String,

    @Column(name = "full_name", length = 200)
    var fullName: String? = null,

    @Column(length = 20)
    var phone: String? = null,

    @Enumerated(EnumType.STRING)
    @Column(length = 50)
    var role: UserRole = UserRole.USER,

    @Column(name = "is_active")
    var isActive: Boolean = true,

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    var createdAt: LocalDateTime? = null,

    @UpdateTimestamp
    @Column(name = "updated_at")
    var updatedAt: LocalDateTime? = null,

    @Column(name = "last_login")
    var lastLogin: LocalDateTime? = null
)

enum class UserRole{
    ADMIN, USER;

    companion object {
        fun fromString(role: String): UserRole {
            return try {
                valueOf(role.uppercase())
            } catch (e: IllegalArgumentException) {
                USER
            }
        }
    }
}