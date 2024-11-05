package com.example.spring.aop

import org.aspectj.lang.JoinPoint
import org.aspectj.lang.annotation.After
import org.aspectj.lang.annotation.AfterReturning
import org.aspectj.lang.annotation.AfterThrowing
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Before

@Aspect
class Aspect {
    @Before("PointCut.servicePointCut()")
    fun serviceStart(point: JoinPoint) {
        val className = point.target.javaClass.simpleName
        val methodName = point.signature.name
        val arguments = point.args
    }
    @Around("PointCut.servicePointCut()")
    fun serviceAround(point: JoinPoint) {
    }

    @After("PointCut.servicePointCut()")
    fun serviceAfter(point: JoinPoint) {
    }

    @AfterReturning("PointCut.servicePointCut()")
    fun serviceAfterReturning(point: JoinPoint) {
    }

    @AfterThrowing("PointCut.servicePointCut()")
    fun serviceAfterThrowing(point: JoinPoint) {
    }
}