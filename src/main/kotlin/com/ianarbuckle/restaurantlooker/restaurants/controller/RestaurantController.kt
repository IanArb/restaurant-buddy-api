package com.ianarbuckle.restaurantlooker.restaurants.controller

import com.ianarbuckle.restaurantlooker.restaurants.model.Restaurant
import com.ianarbuckle.restaurantlooker.restaurants.service.RestaurantService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

/**
 * @author ianarbuckle on 21/05/2018.
 */
@RequestMapping("/restaurants")
@RestController
class RestaurantController {

    @Autowired
    private lateinit var service: RestaurantService

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun saveRestaurant(@RequestBody restaurant: Restaurant) = service.saveRestaurant(restaurant)

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    fun retrieveRestaurants(): MutableList<Restaurant> = service.findAllRestaurants()

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    fun deleteRestaurants(@PathVariable id: String): Restaurant = service.deleteRestaurantsById(id)

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    fun updateRestaurants(@RequestBody restaurant: Restaurant) = service.updateRestaurant(restaurant)

}