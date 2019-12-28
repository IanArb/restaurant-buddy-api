package com.ianarbuckle.restaurantlooker.authentication.controller

import com.ianarbuckle.restaurantlooker.authentication.model.AuthBody
import com.ianarbuckle.restaurantlooker.authentication.model.User
import com.ianarbuckle.restaurantlooker.authentication.service.CustomUserDetailsService
import com.ianarbuckle.restaurantlooker.security.JwtTokenProvider
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.http.ResponseEntity.ok
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.AuthenticationException
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*

/**
 * @author ianarbuckle on 21/09/2019.
 */
@RestController
@RequestMapping("/authentication")
class UserController {

    @Autowired
    lateinit var authenticationManager: AuthenticationManager

    @Autowired
    lateinit var jwtTokenTokenProvider: JwtTokenProvider

    @Autowired
    lateinit var userService: CustomUserDetailsService

    @PostMapping("/login")
    fun login(@RequestBody data: AuthBody): ResponseEntity<*> {
        try {
            val username = data.email
            val model = HashMap<Any, Any>()
            authenticationManager.authenticate(UsernamePasswordAuthenticationToken(username, data.password))
            if (data.isRefresh) {
                val refreshToken = jwtTokenTokenProvider.createRefreshToken(username, userService.findUserByEmail(username)?.roles!!)
                model["username"] = username
                model["token"] = refreshToken
            } else {
                val token = jwtTokenTokenProvider.createToken(username, userService.findUserByEmail(username)?.roles!!)
                model["username"] = username
                model["token"] = token
            }
            return ok(model)
        } catch (e: AuthenticationException) {
            throw BadCredentialsException("Invalid email/password supplied")
        }
    }

    @PostMapping("/register")
    fun register(@RequestBody user: User): ResponseEntity<*> {
        userService.findAll().forEach {
            if (it.email == user.email) {
                throw BadCredentialsException("User with username: " + user.email + " already exists")
            }
        }
        userService.saveUser(user)
        val model = HashMap<Any, Any>()
        model["message"] = "User registered successfully"
        return ok(model)
    }
}