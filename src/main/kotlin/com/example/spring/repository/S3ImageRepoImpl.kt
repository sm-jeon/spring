package com.example.spring.repository

import com.amazonaws.services.s3.AmazonS3Client
import com.amazonaws.services.s3.model.CannedAccessControlList
import com.amazonaws.services.s3.model.PutObjectRequest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.io.File

@Component
class S3ImageRepoImpl @Autowired constructor(
    private val s3Client: AmazonS3Client,
    @Value("\${aws.s3.bucket}") val bucket: String
): ImageRepo {

    override fun upload(file: File, dir: String): String {
        val fileName = "${dir}/${file.name}"
        s3Client.putObject(PutObjectRequest(bucket, fileName, file).withCannedAcl(CannedAccessControlList.PublicRead))
        file.delete()
        return s3Client.getUrl(bucket, fileName).toString()
    }
}