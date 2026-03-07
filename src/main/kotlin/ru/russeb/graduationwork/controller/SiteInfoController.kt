package ru.russeb.graduationwork.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping


@Controller
class SiteInfoController() {
    @GetMapping("/about-us")
    fun aboutUs(): String {
        return "about-us"
    }
}