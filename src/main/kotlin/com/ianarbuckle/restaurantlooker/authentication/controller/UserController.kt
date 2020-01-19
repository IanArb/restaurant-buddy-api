package com.ianarbuckle.restaurantlooker.authentication.controller

import com.ianarbuckle.restaurantlooker.authentication.model.AuthBody
import com.ianarbuckle.restaurantlooker.authentication.model.User
import com.ianarbuckle.restaurantlooker.authentication.service.CustomUserDetailsService
import com.ianarbuckle.restaurantlooker.exception.UserNotFoundException
import com.ianarbuckle.restaurantlooker.security.JwtTokenProvider
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.http.ResponseEntity.ok
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.AuthenticationException
import org.springframework.web.bind.annotation.*
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
            val uuid = data.uuid
            val model = HashMap<Any, Any?>()
            authenticationManager.authenticate(UsernamePasswordAuthenticationToken(uuid, data.password))
            if (data.isRefresh) {
                val refreshToken = userService.findUserByUuid(uuid)?.roles?.let { jwtTokenTokenProvider.createRefreshToken(uuid, it) }
                model["uuid"] = uuid
                model["token"] = refreshToken
            } else {
                val token = userService.findUserByUuid(uuid)?.roles?.let { jwtTokenTokenProvider.createToken(uuid, it) }
                model["uuid"] = uuid
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

    @GetMapping("/retrieveUser")
    fun retrieveUser(@RequestParam uuid: String): User? {
        val users = userService.findAll()

        val user = users.find { it.uuid == uuid }

        if (users.contains(user)) {
            return userService.findUserByUuid(uuid)
        } else {
            throw UserNotFoundException()
        }
    }

}