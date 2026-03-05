package ru.russeb.graduationwork.controller

import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import ru.russeb.graduationwork.dto.UserDeleteRequestDto
import ru.russeb.graduationwork.dto.UserInfoUpdateRequestDto
import ru.russeb.graduationwork.dto.UserPasswordUpdateRequestDto
import ru.russeb.graduationwork.dto.UserResponseDto
import ru.russeb.graduationwork.entity.User
import ru.russeb.graduationwork.service.UserService

@Controller
class UserController(private val userService: UserService,private val passwordEncoder: PasswordEncoder) {
    @GetMapping("/profile")
    fun profile(model: Model,authentication: Authentication): String {
        model.addAttribute("user", UserResponseDto.from(userService.findByEmail(authentication.name).get()))
        return "profile"
    }


    @PostMapping("/profile/update/info")
    fun updateProfileInfo(@RequestBody updateDto: UserInfoUpdateRequestDto, authentication: Authentication): ResponseEntity<Map<String, Any>>  {
        val currentUser = userService.findByEmail(authentication.name).get()
        currentUser.email = updateDto.email
        currentUser.fullName = updateDto.fullName
        currentUser.phone = updateDto.phone

        return try {
            val updatedUser = userService.save(currentUser)

           val newUserDetails = User(
                id = updatedUser.id,
                passwordHash = currentUser.password,
                email = updatedUser.email,
                fullName = updatedUser.fullName,
                phone = updatedUser.phone,
            )

            val authentication = UsernamePasswordAuthenticationToken(
                newUserDetails,
                currentUser.password,
                currentUser.authorities
            )

            SecurityContextHolder.getContext().authentication = authentication

            ResponseEntity.ok(
                mapOf(
                    "success" to true,
                    "message" to "Данные успешно обновлены",
                    "user" to mapOf(
                        "fullName" to updatedUser.fullName,
                        "email" to updatedUser.email,
                        "phone" to updatedUser.phone
                    )
                )
            )
        } catch (e: Exception) {
            ResponseEntity.badRequest().body(
                mapOf(
                    "success" to false,
                    "message" to  e.message.toString()
                )
            )
        }
    }

    @PostMapping("/profile/update/password")
    fun updatePassword(@RequestBody updateDto: UserPasswordUpdateRequestDto, authentication: Authentication): ResponseEntity<Map<String, Any>> {
        return try {

            if (!passwordEncoder.matches(updateDto.currentPassword, userService.findByEmail(authentication.name).get()
            .password)) {
                return ResponseEntity.badRequest().body(mapOf(
                    "success" to false,
                    "message" to "Текущий пароль неверен"
                ))
            }

            if (updateDto.newPassword.length < 8) {
                return ResponseEntity.badRequest().body(mapOf(
                    "success" to false,
                    "message" to "Пароль должен содержать минимум 8 символов"
                ))
            }

            if (updateDto.currentPassword == updateDto.newPassword) {
                return ResponseEntity.badRequest().body(mapOf(
                    "success" to false,
                    "message" to "Новый пароль должен отличаться от текущего"
                ))
            }

            val currentUser = userService.findByEmail(authentication.name).get()
            currentUser.passwordHash = passwordEncoder.encode(updateDto.newPassword)!!


            userService.save(currentUser)

            ResponseEntity.ok(mapOf(
                "success" to true,
                "message" to "Пароль успешно изменен"
            ))
        } catch (e: Exception) {
            ResponseEntity.badRequest().body(mapOf(
                "success" to false,
                "message" to e.message.toString()
            ))
        }
    }


    @PostMapping("/profile/delete-account")
    fun deleteUser(@RequestBody deleteUserDto: UserDeleteRequestDto, authentication: Authentication): ResponseEntity<Map<String, Any>> {
        val currentUser = userService.findByEmail(authentication.name).get()
        return try {
            // Проверяем пароль
            if (!passwordEncoder.matches(deleteUserDto.password, currentUser.password)) {
                return ResponseEntity.badRequest().body(mapOf(
                    "success" to false,
                    "message" to "Неверный пароль"
                ))
            }

            // Удаляем аккаунт
            userService.delete(currentUser)

            ResponseEntity.ok(mapOf(
                "success" to true,
                "message" to "Аккаунт успешно удален"
            ))
        } catch (e: Exception) {
            ResponseEntity.badRequest().body(mapOf(
                "success" to false,
                "message" to e.message.toString()
            ))
        }
    }

    @GetMapping("/cart")
    fun cart(): String {
        return "cart"
    }
}