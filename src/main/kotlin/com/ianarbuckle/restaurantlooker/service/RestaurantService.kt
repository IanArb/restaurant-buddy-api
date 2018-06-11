package com.ianarbuckle.restaurantlooker.service

import com.ianarbuckle.restaurantlooker.model.Restaurant

/**
 * @author ianarbuckle on 21/05/2018.
 */
interface RestaurantService {
    fun findAllRestaurants(): MutableList<Restaurant>
    fun saveRestaurant(restaurant: Restaurant)
    fun deleteRestaurantsById(id: String): MutableList<Restaurant>
    fun updateRestaurant(restaurant: Restaurant)

}