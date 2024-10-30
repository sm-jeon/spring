package com.example.spring.controller

import com.example.spring.controller.dto.AddPostDTO
import com.example.spring.entity.Post
import com.example.spring.service.SseService
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter

@RequestMapping("/sse")
@RestController
@SessionAttributes(value = ["user_id"])
class SseController @Autowired constructor(
    private val sseService: SseService,
) {

    @ApiResponses(ApiResponse(responseCode = "200"))
    @GetMapping("/connect", produces = [org.springframework.http.MediaType.TEXT_EVENT_STREAM_VALUE])
    fun connectSse(
        @Parameter(hidden = true) @ModelAttribute("user_id") userId: Long,
    ): ResponseEntity<SseEmitter> {
        val emitter = sseService.register(userId)
        return ResponseEntity.ok(emitter)
    }
}