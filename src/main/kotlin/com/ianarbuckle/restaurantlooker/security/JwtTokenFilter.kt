package com.ianarbuckle.restaurantlooker.security

import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.filter.GenericFilterBean
import java.io.IOException
import javax.servlet.FilterChain
import javax.servlet.ServletException
import javax.servlet.ServletRequest
import javax.servlet.ServletResponse
import javax.servlet.http.HttpServletRequest

/**
 * @author ianarbuckle on 21/09/2019.
 */
class JwtTokenFilter(private val tokenTokenProvider: JwtTokenProvider) : GenericFilterBean() {

    @Throws(IOException::class, ServletException::class)
    override fun doFilter(req: ServletRequest, res: ServletResponse, filterChain: FilterChain) {
        val token = tokenTokenProvider.resolveToken(req as HttpServletRequest)
        if (!token.isNullOrEmpty() && tokenTokenProvider.validateToken(token)) {
            val auth = tokenTokenProvider.getAuthentication(token)
            SecurityContextHolder.getContext().authentication = auth
        }
        filterChain.doFilter(req, res)
    }
}