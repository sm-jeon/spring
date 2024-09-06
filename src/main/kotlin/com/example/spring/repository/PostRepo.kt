package com.example.spring.repository

import com.example.spring.entity.Post
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface PostRepo: CrudRepository<Post, Long> {
}