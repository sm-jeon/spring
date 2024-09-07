package com.example.spring.controller.dto

import com.example.spring.entity.Post
import com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy
import com.fasterxml.jackson.databind.annotation.JsonNaming

@JsonNaming(SnakeCaseStrategy::class)
class AddPostDTO(
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