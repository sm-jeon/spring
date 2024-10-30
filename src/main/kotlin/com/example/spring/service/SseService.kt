package com.example.spring.service

import com.example.spring.repository.SseRepo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter.SseEventBuilder
import java.io.IOException

@Service
class SseService @Autowired constructor(
    private val sseRepo: SseRepo
){
    fun register(userId: Long): SseEmitter {
        sseRepo.findByUserId(userId)?.complete()
        val sseEmitter = SseEmitter(SSE_EMITTER_TIMEOUT)
        sendEvent(sseEmitter, SseEmitter.event().name("dummy").data("connect").reconnectTime(SSE_EMITTER_RECONNECT_TIME))
        return sseEmitter
    }

    fun sendEvent(targetId: Long, data: Pair<String, String>): Boolean {
        val emitter = sseRepo.findByUserId(targetId) ?: return false
        return sendEvent(emitter, SseEmitter.event().name(data.first).data(data.second))
    }

    private fun sendEvent(emitter: SseEmitter, sseEventBuilder: SseEventBuilder): Boolean {
        try {
            emitter.send(sseEventBuilder)
            return true
        } catch (e: IOException) {
            emitter.complete()
        }
        return false
    }

    companion object {
        private const val SSE_EMITTER_TIMEOUT = 300_000L
        private const val SSE_EMITTER_RECONNECT_TIME = 500L
    }
}