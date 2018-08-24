package com.ianarbuckle.restaurantlooker.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document



/**
 * @author ianarbuckle on 21/05/2018.
 */

@Document
data class Restaurant(@Id val id: String?, val results: MutableList<Restaurants>)

@Document
data class Restaurants(val restaurantName: String, val description: String, val county: String, val street: String,
                       val address: String, val location: Location, val status: String, val menu: Menu, val imageUrl: String)

@Document
data class Location(val latitude: Float, val longitude: Float)

@Document
data class Menu(val lunch: MutableList<Dish>, val dinner: MutableList<Dish>)

@Document
data class Dish(val courseType: String, val dishName: String, val description: String, val price: Price)

@Document
data class Price(val currency: String, val amount: Float)


