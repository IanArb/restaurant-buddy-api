package com.ianarbuckle.restaurantlooker.service

import com.ianarbuckle.restaurantlooker.model.Restaurant
import com.ianarbuckle.restaurantlooker.repository.RestaurantRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 * @author ianarbuckle on 21/05/2018.
 */
@Service
class RestaurantServiceImpl : RestaurantService {

    @Autowired
    private lateinit var repository: RestaurantRepository

    override fun findAllRestaurants(): MutableList<Restaurant> = repository.findAll()

    override fun saveRestaurant(restaurant: Restaurant) {
        repository.save(restaurant)
    }

    override fun deleteRestaurantsById(id: String): MutableList<Restaurant> = repository.deleteRestaurantById(id)

    override fun updateRestaurant(restaurant: Restaurant) {
        repository.save(restaurant)
    }
}