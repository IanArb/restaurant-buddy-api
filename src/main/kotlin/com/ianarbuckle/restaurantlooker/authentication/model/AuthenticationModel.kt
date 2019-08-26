package com.ianarbuckle.restaurantlooker.authentication.model

import org.springframework.data.annotation.Id

/**
 * @author ianarbuckle on 01/08/2019.
 */

data class Authentication(@Id val uuid: String, val user: User)

data class User(val type: String, val username: String, val password: String)
