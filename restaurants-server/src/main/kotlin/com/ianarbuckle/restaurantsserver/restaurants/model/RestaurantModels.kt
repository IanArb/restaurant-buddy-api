package com.ianarbuckle.restaurantsserver.restaurants.model

import io.swagger.annotations.ApiModel
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDateTime
import javax.validation.constraints.NotEmpty


/**
 * @author ianarbuckle on 21/05/2018.
 */

@ApiModel(description = "Details of Restaurant")
@Document
data class Restaurant(@Id val id: String?,
                      @CreatedDate
                      val createdAt: LocalDateTime?,
                      @NotEmpty
                      val restaurantName: String?,
                      val description: String?,
                      @NotEmpty
                      val county: String?,
                      @NotEmpty
                      val street: String?,
                      @NotEmpty
                      val address: String?,
                      @NotEmpty
                      val location: Location?,
                      @NotEmpty
                      val status: String?,
                      @NotEmpty
                      val dishes: MutableList<Dish>?,
                      @NotEmpty
                      val imageUrl: String?)

@Document
data class Location(val latitude: Float, val longitude: Float)

@Document
data class Dish(val courseType: String, val dishName: String, val description: String, val price: Price)

@Document
data class Price(val currency: String, val amount: Float)


