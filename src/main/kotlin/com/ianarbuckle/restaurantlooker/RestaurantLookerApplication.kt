package com.ianarbuckle.restaurantlooker

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.mongodb.config.EnableMongoAuditing
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories

@SpringBootApplication
@EnableMongoAuditing
@EnableMongoRepositories
class RestaurantLookerApplication

fun main(args: Array<String>) {
    runApplication<RestaurantLookerApplication>(*args)
}
