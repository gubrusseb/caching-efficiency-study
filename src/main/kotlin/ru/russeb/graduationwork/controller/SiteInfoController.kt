package ru.russeb.graduationwork.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping


@Controller
class SiteInfoController {
    @GetMapping("/about_us")
    fun about_us(): String {
        return "about_us"
    }

    @GetMapping("/profile")
    fun profile(): String {
        return "profile"
    }

    @GetMapping("/cart")
    fun cart(): String {
        return "cart"
    }
}