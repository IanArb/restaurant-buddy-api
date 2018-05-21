package com.ianarbuckle.restaurantlooker

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class RestaurantLookerApplication

fun main(args: Array<String>) {
    runApplication<RestaurantLookerApplication>(*args)
}
