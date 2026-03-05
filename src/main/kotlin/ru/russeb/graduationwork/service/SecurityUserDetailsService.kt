package ru.russeb.graduationwork.service

import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import ru.russeb.graduationwork.repository.UserRepository

@Service
class SecurityUserDetailsService(
    private val userRepository: UserRepository  // Репозиторий для доступа к БД
) : UserDetailsService {

    override fun loadUserByUsername(email: String): UserDetails {
        return userRepository.findByEmail(email)
            .orElseThrow {
                UsernameNotFoundException("Пользователь с email $email не найден")
            }
    }
}