package com.ianarbuckle.restaurantlooker.restaurants.service

import com.ianarbuckle.restaurantlooker.restaurants.model.Restaurants
import com.ianarbuckle.restaurantlooker.restaurants.repository.RestaurantRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 * @author ianarbuckle on 07/05/2019.
 */
interface RestaurantService {
    fun findAllRestaurants(): MutableList<Restaurants>
    fun saveRestaurant(restaurant: Restaurants)
    fun deleteRestaurantsById(id: String): MutableList<Restaurants>
    fun updateRestaurant(restaurant: Restaurants)
}

@Service
class RestaurantServiceImpl : RestaurantService {

    @Autowired
    private lateinit var repository: RestaurantRepository

    override fun findAllRestaurants(): MutableList<Restaurants> = repository.findAll()

    override fun saveRestaurant(restaurant: Restaurants) {
        repository.save(restaurant)
    }

    override fun deleteRestaurantsById(id: String): MutableList<Restaurants> = repository.deleteRestaurantById(id)

    override fun updateRestaurant(restaurant: Restaurants) {
        repository.save(restaurant)
    }
}