package com.example.spring.repository

import org.springframework.web.servlet.mvc.method.annotation.SseEmitter

interface SseRepo {

    fun findByUserId(userId: Long): SseEmitter?
    fun register(userId: Long, emitter: SseEmitter)
}