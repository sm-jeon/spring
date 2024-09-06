package com.example.spring.repository

import com.example.spring.entity.Session
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface SessionRepo: CrudRepository<Session, String> {
}