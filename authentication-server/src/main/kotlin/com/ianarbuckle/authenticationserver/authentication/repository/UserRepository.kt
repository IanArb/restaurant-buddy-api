package com.ianarbuckle.authenticationserver.authentication.repository

import com.ianarbuckle.authenticationserver.authentication.model.User
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

/**
 * @author ianarbuckle on 21/09/2019.
 */
@Repository
interface UserRepository : MongoRepository<User, String> {
    fun findByUuid(uuid: String): User
}