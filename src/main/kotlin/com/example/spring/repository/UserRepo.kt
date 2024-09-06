package com.example.spring.repository

import com.example.spring.entity.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepo: JpaRepository<User, Long> {
    fun findByName(name: String): User?
}