package com.ianarbuckle.restaurantlooker.authentication.controller

import com.ianarbuckle.restaurantlooker.authentication.model.Authentication
import com.ianarbuckle.restaurantlooker.authentication.service.AuthenticationService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

/**
 * @author ianarbuckle on 01/08/2019.
 */
@RequestMapping("/authentication")
@RestController
class AuthenticationController {

    @Autowired
    lateinit var service: AuthenticationService

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createUser(@RequestBody authentication: Authentication) = service.saveUser(authentication)

    @GetMapping("{uuid}")
    @ResponseStatus(HttpStatus.OK)
    fun retrieveUserByUuid(@PathVariable uuid: String): Authentication = service.findAuthenticationByUuid(uuid)

    @DeleteMapping("{uuid}")
    @ResponseStatus(HttpStatus.OK)
    fun deleteUserByUuid(@PathVariable uuid: String) = service.deleteAuthenticationByUuid(uuid)
}