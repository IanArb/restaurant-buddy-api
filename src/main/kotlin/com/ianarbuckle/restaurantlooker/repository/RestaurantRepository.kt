package com.ianarbuckle.restaurantlooker.repository

import com.ianarbuckle.restaurantlooker.model.Restaurant
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

/**
 * @author ianarbuckle on 21/05/2018.
 */
@Repository
interface RestaurantRepository : MongoRepository<Restaurant, String> {
    fun deleteRestaurantById(id: String): MutableList<Restaurant>
}