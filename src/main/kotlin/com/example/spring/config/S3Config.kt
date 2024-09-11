package com.example.spring.config

import com.amazonaws.auth.AWSStaticCredentialsProvider
import com.amazonaws.auth.BasicAWSCredentials
import com.amazonaws.services.s3.AmazonS3Client
import com.amazonaws.services.s3.AmazonS3ClientBuilder
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class S3Config (
    @Value("\${aws.credentials.access-key}") val accessKey: String,
    @Value("\${aws.credentials.secret-key}") val secretKey: String,
    @Value("\${aws.credentials.region}") val region: String,
){

    @Bean
    fun amazonS3Factory() = BasicAWSCredentials(accessKey, secretKey).let {
        AmazonS3ClientBuilder.standard()
            .withCredentials(AWSStaticCredentialsProvider(it))
            .withRegion(region)
            .build() as AmazonS3Client
    }
}