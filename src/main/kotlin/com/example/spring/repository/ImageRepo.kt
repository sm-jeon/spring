package com.example.spring.repository

import java.io.File

interface ImageRepo {
    fun upload(file: File, dir: String): String
}