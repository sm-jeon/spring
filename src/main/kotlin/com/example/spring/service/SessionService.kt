package com.example.spring.service

import com.example.spring.entity.Session
import com.example.spring.repository.SessionRepo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class SessionService @Autowired constructor(
    private val sessionRepo: SessionRepo
) {
    fun register(session: Session) {
        sessionRepo.save(session)
    }

    fun validate(session: Session): Boolean {
        return session.id
            ?.let { sessionRepo.findByIdOrNull(it) }
            ?.let { it.userId == session.userId }
            ?: false
    }
}