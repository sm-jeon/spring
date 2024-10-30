package com.example.spring.repository

import org.springframework.stereotype.Component
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter

@Component
class SseRepoImpl: SseRepo {

    private val sseEmitterMap = mutableMapOf<Long, MutableSet<SseEmitter>>()

    override fun findByUserId(userId: Long): SseEmitter? {
        return sseEmitterMap[userId]?.firstOrNull()
    }

    override fun register(userId: Long, emitter: SseEmitter) {
        val emitters = sseEmitterMap.getOrDefault(userId, mutableSetOf())
        emitters.add(emitter)
        sseEmitterMap[userId] = emitters
        emitter.onCompletion { emitters.remove(emitter) }
    }
}