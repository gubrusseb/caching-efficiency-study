package ru.russeb.graduationwork.controller

import jakarta.servlet.http.HttpServletRequest
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ModelAttribute

@ControllerAdvice
class GlobalControllerAdvice {

    @ModelAttribute("currentUrl")
    fun addCurrentUrl(request: HttpServletRequest): String {
        return request.requestURI
    }
}