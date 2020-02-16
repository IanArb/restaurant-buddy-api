package com.ianarbuckle.restaurantsserver

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class RestaurantsServerApplication

fun main(args: Array<String>) {
	runApplication<RestaurantsServerApplication>(*args)
}
