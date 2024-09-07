package com.example.spring.service

import com.example.spring.entity.Post
import com.example.spring.repository.PostRepo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class PostService @Autowired constructor(
    private val postRepo: PostRepo
) {
    fun getAllPost(): Iterable<Post> {
        return postRepo.findAll()
    }

    fun addPost(post: Post): Post {
        return postRepo.save(post)
    }
}