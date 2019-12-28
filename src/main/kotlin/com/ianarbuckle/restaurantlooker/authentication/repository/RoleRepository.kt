package com.ianarbuckle.restaurantlooker.authentication.repository

import com.ianarbuckle.restaurantlooker.authentication.model.Role
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

/**
 * @author ianarbuckle on 22/09/2019.
 */
@Repository
interface RoleRepository : MongoRepository<Role, String> {
    fun findByRole(role: String): Role
}