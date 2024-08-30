package com.example.spring.service

import com.example.spring.entity.User
import com.example.spring.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserService @Autowired constructor(
    val userRepository: UserRepository,
    val encoder: BCryptPasswordEncoder,
) {
    fun addUser(user: User): User? {
        user.password = encoder.encode(user.password)
        if(userRepository.findByName(user.name) != null) return null
        return userRepository.save(user)
    }

    fun exist(user: User): Boolean {
        return userRepository.findByName(user.name)?.let {
            encoder.matches(user.password, it.name)
        } ?: false
    }
}