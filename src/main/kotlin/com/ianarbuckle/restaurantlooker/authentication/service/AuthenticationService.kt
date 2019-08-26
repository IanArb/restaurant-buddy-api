package com.ianarbuckle.restaurantlooker.authentication.service

import com.ianarbuckle.restaurantlooker.authentication.model.Authentication
import com.ianarbuckle.restaurantlooker.authentication.repository.AuthenticationRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 * @author ianarbuckle on 01/08/2019.
 */
interface AuthenticationService {
    fun saveUser(authentication: Authentication)
    fun findAuthenticationByUuid(uuid: String): Authentication
    fun deleteAuthenticationByUuid(uuid: String): Authentication
}

@Service
class AuthenticationServiceImpl : AuthenticationService {

    @Autowired
    lateinit var repository: AuthenticationRepository

    override fun saveUser(authentication: Authentication) {
        repository.save(authentication)
    }

    override fun findAuthenticationByUuid(uuid: String): Authentication = repository.findAuthenticationByUuid(uuid)

    override fun deleteAuthenticationByUuid(uuid: String): Authentication {
        return repository.findAuthenticationByUuid(uuid).apply {
            repository.delete(this)
        }
    }
}