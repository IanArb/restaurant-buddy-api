package com.ianarbuckle.restaurantlooker.utils

import com.ianarbuckle.restaurantlooker.model.Location
import com.ianarbuckle.restaurantlooker.model.Restaurant
import com.ianarbuckle.restaurantlooker.model.Restaurants

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
                    Location(0.5f, 0.10f), "OPEN"))
            restaurants.add(Restaurants("Cirillo's", "Description", "Dublin", "Dublin", "Dublin",
                    Location(0.5f, 0.10f), "OPEN"))
            restaurants.add(Restaurants("Cirillo's", "Description", "Dublin", "Dublin", "Dublin",
                    Location(0.5f, 0.10f), "OPEN"))
            restaurants.add(Restaurants("Cirillo's", "Description", "Dublin", "Dublin", "Dublin",
                    Location(0.5f, 0.10f), "OPEN"))
            restaurants.add(Restaurants("Cirillo's", "Description", "Dublin", "Dublin", "Dublin",
                    Location(0.5f, 0.10f), "OPEN"))
            restaurants.add(Restaurants("Cirillo's", "Description", "Dublin", "Dublin", "Dublin",
                    Location(0.5f, 0.10f), "OPEN"))
            restaurants.add(Restaurants("Cirillo's", "Description", "Dublin", "Dublin", "Dublin",
                    Location(0.5f, 0.10f), "OPEN"))
            restaurants.add(Restaurants("Cirillo's", "Description", "Dublin", "Dublin", "Dublin",
                    Location(0.5f, 0.10f), "OPEN"))

            return restaurants
        }
    }


}