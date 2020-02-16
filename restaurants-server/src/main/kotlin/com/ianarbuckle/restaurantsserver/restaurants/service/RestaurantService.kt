package com.ianarbuckle.restaurantsserver.restaurants.service

import com.ianarbuckle.restaurantsserver.restaurants.model.Restaurant
import com.ianarbuckle.restaurantsserver.restaurants.repository.RestaurantRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 * @author ianarbuckle on 07/05/2019.
 */
interface RestaurantService {
    fun findAllRestaurants(): MutableList<Restaurant>
    fun saveRestaurant(restaurant: Restaurant)
    fun deleteRestaurantsById(id: String): Restaurant
    fun updateRestaurant(restaurant: Restaurant): Restaurant?
}

@Service
class RestaurantServiceImpl : RestaurantService {

    @Autowired
    private lateinit var repository: RestaurantRepository

    override fun findAllRestaurants(): MutableList<Restaurant> = repository.findAll()

    override fun saveRestaurant(restaurant: Restaurant) {
        repository.save(restaurant)
    }

    override fun deleteRestaurantsById(id: String): Restaurant {
        return repository.findRestaurantById(id).apply {
            repository.delete(this)
        }
    }

    override fun updateRestaurant(restaurant: Restaurant): Restaurant? {
        return repository.save(restaurant).takeIf { repository.existsById(restaurant.id!!) }
    }
}