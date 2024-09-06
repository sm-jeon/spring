package com.example.spring.service

import com.example.spring.entity.User
import com.example.spring.repository.UserRepo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserService @Autowired constructor(
    private val userRepo: UserRepo,
    private val encoder: BCryptPasswordEncoder,
) {
    fun addUser(user: User): User? {
        user.password = encoder.encode(user.password)
        if(userRepo.findByName(user.name) != null) return null
        return userRepo.save(user)
    }

    fun exist(user: User): Boolean {
        return userRepo.findByName(user.name)?.let {
            encoder.matches(user.password, it.name)
        } ?: false
    }
}