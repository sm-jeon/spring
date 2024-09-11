package com.example.spring.controller.dto

import com.example.spring.entity.Post
import com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy
import com.fasterxml.jackson.databind.annotation.JsonNaming
import org.springframework.web.bind.annotation.RequestPart
import org.springframework.web.multipart.MultipartFile

@JsonNaming(SnakeCaseStrategy::class)
class AddPostDTO(
    @RequestPart val image: MultipartFile?,
    val title: String,
    val content: String,
) {
    fun toPost(userId: Long) = Post(
        0,
        title,
        content,
        userId
    )
}