package com.ianarbuckle.restaurantlooker.authentication.repository

import com.ianarbuckle.restaurantlooker.authentication.model.Authentication
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

/**
 * @author ianarbuckle on 01/08/2019.
 */
@Repository
interface AuthenticationRepository : MongoRepository<Authentication, String> {
    fun findAuthenticationByUuid(uuid: String): Authentication
}