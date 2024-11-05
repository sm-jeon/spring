package com.example.spring.aop

import org.aspectj.lang.annotation.Pointcut

class PointCut {
    @Pointcut("execution(* com.example.spring.service..*Service.*(..))")
    fun servicePointCut() {}
}