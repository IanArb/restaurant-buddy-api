package com.ianarbuckle.restaurantlooker.restaurants.repository

import com.ianarbuckle.restaurantlooker.restaurants.model.Restaurants
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

/**
 * @author ianarbuckle on 21/05/2018.
 */
@Repository
interface RestaurantRepository : MongoRepository<Restaurants, String> {
    fun deleteRestaurantById(id: String): MutableList<Restaurants>
}