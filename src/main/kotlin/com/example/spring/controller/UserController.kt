package com.example.spring.controller

import jakarta.servlet.http.HttpSession
import org.springframework.http.ResponseEntity
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class UserController {
    @PostMapping("/login")
    fun signIn(
        @RequestBody password: String,
        session: HttpSession,
        model: Model
    ) {
       session.id
    }

    @PostMapping("/signup")
    fun signUp() {}

    @PostMapping("/signout")
    fun signOut() {}
}