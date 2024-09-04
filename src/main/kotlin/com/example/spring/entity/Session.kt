package com.example.spring.entity

import jakarta.persistence.Id
import org.springframework.data.redis.core.RedisHash


@RedisHash(value = "session", timeToLive = 60)
class Session(
    @Id
    val id: String? = null,
    val userId: String? = null,
)