package com.ianarbuckle.restaurantlooker.utils

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.ObjectMapper
import com.ianarbuckle.restaurantlooker.model.*
import org.springframework.http.MediaType
import java.io.IOException
import java.nio.charset.Charset


/**
 * @author ianarbuckle on 28/05/2018.
 */
class TestUtils {

    companion object RestaurantMockData {

        fun buildRestaurantMock() : MutableList<Restaurant> {
            val restaurants = mutableListOf<Restaurant>()

            val restaurantData = Restaurant("1", getDataList())

            restaurants.add(restaurantData)

            return restaurants
        }

        fun getDataList(): MutableList<Restaurants> {
            val restaurants = mutableListOf<Restaurants>()

            restaurants.add(Restaurants("Cirillo's", "Description", "Dublin", "Dublin", "Dublin",
                    Location(0.5f, 0.10f), "OPEN", Menu(createLunchMenu(), createDinnerMenu())))
            restaurants.add(Restaurants("Cirillo's", "Description", "Dublin", "Dublin", "Dublin",
                    Location(0.5f, 0.10f), "OPEN", Menu(createLunchMenu(), createDinnerMenu())))
            restaurants.add(Restaurants("Cirillo's", "Description", "Dublin", "Dublin", "Dublin",
                    Location(0.5f, 0.10f), "OPEN", Menu(createLunchMenu(), createDinnerMenu())))
            restaurants.add(Restaurants("Cirillo's", "Description", "Dublin", "Dublin", "Dublin",
                    Location(0.5f, 0.10f), "OPEN", Menu(createLunchMenu(), createDinnerMenu())))
            restaurants.add(Restaurants("Cirillo's", "Description", "Dublin", "Dublin", "Dublin",
                    Location(0.5f, 0.10f), "OPEN", Menu(createLunchMenu(), createDinnerMenu())))
            restaurants.add(Restaurants("Cirillo's", "Description", "Dublin", "Dublin", "Dublin",
                    Location(0.5f, 0.10f), "OPEN", Menu(createLunchMenu(), createDinnerMenu())))
            restaurants.add(Restaurants("Cirillo's", "Description", "Dublin", "Dublin", "Dublin",
                    Location(0.5f, 0.10f), "OPEN", Menu(createLunchMenu(), createDinnerMenu())))
            restaurants.add(Restaurants("Cirillo's", "Description", "Dublin", "Dublin", "Dublin",
                    Location(0.5f, 0.10f), "OPEN", Menu(createLunchMenu(), createDinnerMenu())))

            return restaurants
        }

        private fun createLunchMenu(): MutableList<Dish> {
            val lunch = mutableListOf<Dish>()

            lunch.add(Dish("STARTER", "Soup of the day", "Fresh soup of the day", Price("EUR", 5.5f)))
            lunch.add(Dish("PIZZA", "MARGHERITA", "Tomato Sauce, Mozzarella, Parmesan & Fresh Basil", Price("EUR", 9f)))
            lunch.add(Dish("DESERT", "Cheese cake", "Strawberry cheese cake", Price("EUR", 6.5f)))

            return lunch
        }

        private fun createDinnerMenu(): MutableList<Dish> {
            val dinner = mutableListOf<Dish>()

            dinner.add(Dish("STARTER", "Soup of the day", "Fresh soup of the day", Price("EUR", 5.5f)))
            dinner.add(Dish("PIZZA", "MARGHERITA", "Tomato Sauce, Mozzarella, Parmesan & Fresh Basil", Price("EUR", 9f)))
            dinner.add(Dish("DESERT", "Cheese cake", "Strawberry cheese cake", Price("EUR", 6.5f)))

            return dinner
        }

        fun asJsonString(obj: Any): String {
            try {
                val mapper = ObjectMapper()
                return mapper.writeValueAsString(obj)
            } catch (e: Exception) {
                throw RuntimeException(e)
            }

        }

    }


}