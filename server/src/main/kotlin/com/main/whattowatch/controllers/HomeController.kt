package com.main.whattowatch.controllers


import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Controller
class HomeController {
    @RequestMapping("/home")
    public fun home(): String {
        return "home"
    }
}