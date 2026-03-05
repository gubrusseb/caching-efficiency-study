package ru.russeb.graduationwork.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import ru.russeb.graduationwork.service.UserService


@Controller
class SiteInfoController() {
    @GetMapping("/about_us")
    fun aboutUs(): String {
        return "about_us"
    }
}