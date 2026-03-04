package ru.russeb.graduationwork.service

import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import ru.russeb.graduationwork.dto.UserCreateRequest
import ru.russeb.graduationwork.dto.UserResponse
import ru.russeb.graduationwork.entity.User
import ru.russeb.graduationwork.repository.UserRepository

@Service
@Transactional
class UserService(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder
) {
    fun createUser(request: UserCreateRequest): UserResponse {
        require(!userRepository.existsByEmail(request.email)) {
            "User with email ${request.email} already exists"
        }

        val user = User(
            email = request.email,
            passwordHash = passwordEncoder.encode(request.password)!!,
            fullName = request.fullName,
            phone = request.phone,
        )

        val savedUser = userRepository.save(user)
        return UserResponse.from(savedUser)
    }

    fun existsByEmail(email: String): Boolean {
        return userRepository.existsByEmail(email)
    }
}