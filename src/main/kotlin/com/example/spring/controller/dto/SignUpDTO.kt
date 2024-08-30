package com.example.spring.controller.dto

import com.example.spring.entity.User
import com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy
import com.fasterxml.jackson.databind.annotation.JsonNaming

@JsonNaming(SnakeCaseStrategy::class)
class SignUpDTO(
    val name: String,
    val password: String,
) {
    fun toUser() = User(0L, name, password)
}