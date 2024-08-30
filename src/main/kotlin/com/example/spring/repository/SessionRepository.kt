package com.example.spring.repository

import com.example.spring.entity.Session
import org.springframework.data.repository.CrudRepository

interface SessionRepository: CrudRepository<Session, String> {
}