package ru.russeb.graduationwork.service

import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import ru.russeb.graduationwork.dto.UserCreateRequestDto
import ru.russeb.graduationwork.entity.User
import ru.russeb.graduationwork.repository.UserRepository
import java.util.Optional

@Service
@Transactional
class UserService(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder
) {
    fun createUser(request: UserCreateRequestDto): User {
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
        return savedUser
    }

    fun existsByEmail(email: String): Boolean {
        return userRepository.existsByEmail(email)
    }

    fun findByEmail(email: String): Optional<User> {
        return userRepository.findByEmail(email)
    }

    fun save(user: User): User {
        return userRepository.save(user)
    }

    fun delete(user: User) {
        userRepository.delete(user)
    }
}