package com.example.spring.controller

import com.example.spring.controller.dto.SignInDTO
import com.example.spring.controller.dto.SignUpDTO
import com.example.spring.entity.Session
import com.example.spring.entity.User
import com.example.spring.service.SessionService
import com.example.spring.service.UserService
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
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

@Tag(name = "User")
@RestController
@SessionAttributes(value = ["user_id"])
class UserController @Autowired constructor(
    private val userService: UserService,
    private val sessionService: SessionService,
) {

    @ApiResponses(
        ApiResponse(responseCode = "200"),
        ApiResponse(responseCode = "401", description = "입력 정보가 잘못됨.", content = [Content()])
    )
    @PostMapping("/signin")
    fun signIn(
        @RequestBody signInDTO: SignInDTO,
        session: HttpSession,
        model: Model
    ): ResponseEntity<Unit> {
        val user = userService.verifyUser(signInDTO.toUser())
        if(user!=null) {
            sessionService.register(Session(session.id, user.id))
            model.addAttribute("user_id", user.id)
            return ResponseEntity.ok().build()
        }
        return ResponseEntity.status(HttpStatusCode.valueOf(401)).build()
    }

    @ApiResponses(
        ApiResponse(responseCode = "200"),
        ApiResponse(responseCode = "409", description = "name이 중복됨", content = [Content()])
    )
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