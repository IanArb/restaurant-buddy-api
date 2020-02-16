package com.ianarbuckle.authenticationserver.authentication.repository

import com.ianarbuckle.authenticationserver.authentication.model.Role
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

/**
 * @author ianarbuckle on 22/09/2019.
 */
@Repository
interface RoleRepository : MongoRepository<Role, String> {
    fun findByRole(role: String): Role
}