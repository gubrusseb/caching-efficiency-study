package ru.russeb.graduationwork.controller

import jakarta.validation.Valid
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.validation.BindingResult
import org.springframework.validation.FieldError
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import ru.russeb.graduationwork.dto.UserCreateRequestDto
import ru.russeb.graduationwork.service.UserService


@Controller
class AuthController(
    private val userService: UserService
) {

    @GetMapping("/login")
    fun sendLoginForm(@RequestParam(required = false) error: String?, model: Model): String {
        if (error == "true") {
            model.addAttribute("loginError", true)
        }

        return "login"
    }

    @PostMapping("/register")
    fun register(
        @Valid @ModelAttribute("user") request: UserCreateRequestDto,
        bindingResult: BindingResult,
        model: Model
    ): String {

        if (bindingResult.hasErrors()) {
            model.addAttribute("user", request)
            return "register"
        }

        // Проверка совпадения паролей
        if (request.password != request.confirmPassword) {
            bindingResult.addError(
                FieldError(
                    "user",
                    "confirmPassword",
                    "Пароли не совпадают"
                )
            )
        }

        // Проверка существования email
        if (userService.existsByEmail(request.email)) {
            bindingResult.addError(
                FieldError(
                    "user",
                    "email",
                    "Пользователь с таким email уже существует"
                )
            )
        }


        // Если есть ошибки - возвращаем форму с ошибками
        if (bindingResult.hasErrors()) {
            model.addAttribute("user", request)
            return "register"
        }

        // Создание пользователя
        try {
            userService.createUser(request)
            model.addAttribute("registrationSuccess", true)
            model.addAttribute("user", UserCreateRequestDto())
            return "register"
        } catch (e: Exception) {
            return "register"
        }
    }

    @GetMapping("/register")
    fun sendRegisterForm(model: Model): String {
        model.addAttribute("user", UserCreateRequestDto())
        return "register"
    }
}