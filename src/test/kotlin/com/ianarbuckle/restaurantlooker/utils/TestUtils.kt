package com.ianarbuckle.restaurantlooker.utils

import com.fasterxml.jackson.annotation.JsonInclude
import com.ianarbuckle.restaurantlooker.model.Location
import com.ianarbuckle.restaurantlooker.model.Restaurant
import com.ianarbuckle.restaurantlooker.model.Restaurants
import com.fasterxml.jackson.databind.ObjectMapper
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

        val APPLICATION_JSON_UTF8 = MediaType(MediaType.APPLICATION_JSON.type, MediaType.APPLICATION_JSON.subtype, Charset.forName("utf8"))

        @Throws(IOException::class)
        fun convertObjectToJsonBytes(`object`: Any): ByteArray {
            val mapper = ObjectMapper()
            mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL)
            return mapper.writeValueAsBytes(`object`)
        }
    }


}