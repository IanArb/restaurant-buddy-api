package com.ianarbuckle.restaurantlooker.repository

import com.ianarbuckle.restaurantlooker.model.Restaurant
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.data.mongodb.repository.Query
import org.springframework.stereotype.Repository

/**
 * @author ianarbuckle on 21/05/2018.
 */
@Repository
interface RestaurantRepository : MongoRepository<Restaurant, String> {
    @Query("{'results.restaurantName': ?0}")
    fun findRestaurantsByRestaurantName(restaurantName: String): MutableList<Restaurant>

    @Query("{'results.street': ?0}")
    fun findRestaurantsByStreet(): MutableList<Restaurant>

    @Query("{'results.country': ?0}")
    fun findRestaurantsByCounty(): MutableList<Restaurant>

    @Query("{'results.status': ?0}")
    fun findRestaurantsByStatus(): MutableList<Restaurant>

    @Query("{'results.location': ?0}")
    fun findRestaurantsByLocation(): MutableList<Restaurant>

    fun deleteRestaurantById(id: String): MutableList<Restaurant>
}