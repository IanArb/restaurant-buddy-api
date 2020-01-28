package com.ianarbuckle.restaurantlooker.restaurants.repository

import com.ianarbuckle.restaurantlooker.restaurants.model.Restaurant
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

/**
 * @author ianarbuckle on 21/05/2018.
 */
@Repository
interface RestaurantRepository : MongoRepository<Restaurant, String> {
    fun findRestaurantById(id: String): Restaurant
}