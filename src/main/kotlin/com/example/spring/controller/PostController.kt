package com.example.spring.controller

import com.example.spring.controller.dto.AddPostDTO
import com.example.spring.entity.Post
import com.example.spring.service.PostService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/post")
@SessionAttributes(value = ["user_id"])
class PostController @Autowired constructor(
    private val postService: PostService
) {

    @PostMapping
    fun addPost(
        @ModelAttribute("user_id") userId: Long,
        @RequestBody postDTO: AddPostDTO,
    ): ResponseEntity<Post> {
        return ResponseEntity.ok(postService.addPost(postDTO.toPost(userId)))
    }
}