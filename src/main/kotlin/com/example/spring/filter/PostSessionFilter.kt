package com.example.spring.filter

import com.example.spring.entity.Session
import com.example.spring.service.SessionService
import jakarta.servlet.Filter
import jakarta.servlet.FilterChain
import jakarta.servlet.ServletRequest
import jakarta.servlet.ServletResponse
import jakarta.servlet.annotation.WebFilter
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.beans.factory.annotation.Autowired

@WebFilter("/post/*", "/see/*")
class PostSessionFilter @Autowired constructor(
    private val sessionService: SessionService,
): Filter {

    override fun doFilter(p0: ServletRequest?, p1: ServletResponse?, p2: FilterChain) {
        val session = (p0 as HttpServletRequest).session
        val sessionId = session.id
        val userId = session.getAttribute("user_id") as? Long

        val validate = userId?.let { sessionService.validate(Session(sessionId, userId)) } ?: false

        if(validate) {
            sessionService.register(Session(sessionId, userId))
            p2.doFilter(p0, p1)
        } else {
            (p1 as HttpServletResponse).sendError(401)
        }
    }
}