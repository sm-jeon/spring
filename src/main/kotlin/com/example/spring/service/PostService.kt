package com.example.spring.service

import com.example.spring.entity.Post
import com.example.spring.repository.ImageRepo
import com.example.spring.repository.PostRepo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.io.File

@Service
class PostService @Autowired constructor(
    private val postRepo: PostRepo,
    private val imageRepo: ImageRepo,
) {
    fun getAllPost(): Iterable<Post> {
        return postRepo.findAll()
    }

    fun addPost(post: Post, file: File? = null): Post {
        post.imageUrl = file?.let { file ->
            imageRepo.upload(file, "images")
        }
        return postRepo.save(post)
    }
}