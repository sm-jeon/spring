package com.example.spring.service

import com.example.spring.entity.Session
import com.example.spring.repository.SessionRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class SessionService @Autowired constructor(
    val repository: SessionRepository
) {
    fun register(session: Session) {
        repository.save(session)
    }

    fun validate(session: Session): Boolean {
        return session.id
            ?.let { repository.findByIdOrNull(it) }
            ?.let { it.userId == session.userId }
            ?: false
    }
}