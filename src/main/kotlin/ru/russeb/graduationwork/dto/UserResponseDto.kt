package ru.russeb.graduationwork.dto

import ru.russeb.graduationwork.entity.User
import ru.russeb.graduationwork.entity.UserRole
import java.time.LocalDateTime

data class UserResponseDto(
    val id: Long,
    val email: String,
    val fullName: String?,
    val phone: String?,
    val role: UserRole,
    val isActive: Boolean,
    val createdAt: LocalDateTime?,
    val lastLogin: LocalDateTime?
) {
    companion object {
        fun from(user: User): UserResponseDto = UserResponseDto(
            id = user.id!!,
            email = user.email,
            fullName = user.fullName,
            phone = user.phone,
            role = user.role,
            isActive = user.isActive,
            createdAt = user.createdAt,
            lastLogin = user.lastLogin
        )
    }
}