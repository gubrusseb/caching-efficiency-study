package ru.russeb.graduationwork.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import ru.russeb.graduationwork.entity.User
import java.util.Optional

@Repository
interface UserRepository : JpaRepository<User, Long> {

    fun existsByEmail(email: String): Boolean

    fun findByEmail(email: String): Optional<User>

    fun save(user: User): User
}