package com.ianarbuckle.restaurantsserver.utils

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import com.ianarbuckle.restaurantsserver.restaurants.model.Dish
import com.ianarbuckle.restaurantsserver.restaurants.model.Location
import com.ianarbuckle.restaurantsserver.restaurants.model.Price
import com.ianarbuckle.restaurantsserver.restaurants.model.Restaurant
import java.time.LocalDateTime
import java.time.Month


/**
 * @author ianarbuckle on 28/05/2018.
 */
object TestUtils {

    fun buildRestaurantsModel(): MutableList<Restaurant> {
        val restaurants = mutableListOf<Restaurant>()

        val restaurantData = createRestaurant()

        restaurants.add(restaurantData)

        return restaurants
    }

    fun createRestaurant(): Restaurant {
        val dateTime = LocalDateTime.of(2020, Month.JANUARY, 1, 10, 10, 30)
        return Restaurant("1", dateTime, "Cirillo's", "Description", "Dublin", "Dublin", "Dublin",
                Location(0.5f, 0.10f), "OPEN", createMenu(), "")
    }

    private fun createMenu(): MutableList<Dish> {
        val lunch = mutableListOf<Dish>()

        lunch.add(Dish("STARTER", "Soup of the day", "Fresh soup of the day", Price("EUR", 5.5f)))
        lunch.add(Dish("PIZZA", "MARGHERITA", "Tomato Sauce, Mozzarella, Parmesan & Fresh Basil", Price("EUR", 9f)))
        lunch.add(Dish("DESERT", "Cheese cake", "Strawberry cheese cake", Price("EUR", 6.5f)))

        return lunch
    }

    fun asJsonString(obj: Any): String {
        try {
            val mapper = ObjectMapper()
            mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
            return mapper.writeValueAsString(obj)
        } catch (e: Exception) {
            throw RuntimeException(e)
        }

    }

}