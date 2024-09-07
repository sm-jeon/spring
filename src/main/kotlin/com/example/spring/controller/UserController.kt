package com.example.spring.controller

import com.example.spring.controller.dto.SignInDTO
import com.example.spring.controller.dto.SignUpDTO
import com.example.spring.entity.Session
import com.example.spring.entity.User
import com.example.spring.service.SessionService
import com.example.spring.service.UserService
import jakarta.servlet.http.HttpSession
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatusCode
import org.springframework.http.ResponseEntity
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.SessionAttributes
import org.springframework.web.bind.support.SessionStatus

@RestController
@SessionAttributes(value = ["user_id"])
class UserController @Autowired constructor(
    private val userService: UserService,
    private val sessionService: SessionService,
) {
    @PostMapping("/signin")
    fun signIn(
        @RequestBody signInDTO: SignInDTO,
        session: HttpSession,
        model: Model
    ): ResponseEntity<Nothing> {
        val user = userService.verifyUser(signInDTO.toUser())
        if(user!=null) {
            sessionService.register(Session(session.id, user.id))
            model.addAttribute("user_id", user.id)
            return ResponseEntity.ok().build()
        }
        return ResponseEntity.status(HttpStatusCode.valueOf(401)).build()
    }

    @PostMapping("/signup")
    fun signUp(
        @RequestBody signUpDTO: SignUpDTO
    ): ResponseEntity<User> {
        val user = userService.addUser(signUpDTO.toUser())
        if(user == null) return ResponseEntity.status(HttpStatusCode.valueOf(409)).build()
        return ResponseEntity.ok(user)
    }

    @PostMapping("/signout")
    fun signOut(
        sessionStatus: SessionStatus
    ) {
        sessionStatus.setComplete()
    }
}