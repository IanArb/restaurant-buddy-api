package com.ianarbuckle.restaurantlooker.booking.model

import com.ianarbuckle.restaurantlooker.restaurants.model.Location
import io.swagger.annotations.ApiModel
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

/**
 * @author ianarbuckle on 07/05/2019.
 *
 */

@ApiModel(description = "Booking details")
@Document
data class Booking(@Id val id: String? = null, val owner: Owner?, val restaurantDetails: RestaurantDetails?, val table: Table?)

@Document
data class RestaurantDetails(val name: String, val imageUrl: String, val address: String, val location: Location)

@Document
data class Owner(val uuid: String, val name: String, val email: String, val phoneNumber: PhoneNumber, val dietaryRequirements: Boolean, val bookingDate: String, val arrivalTime: String)

@Document
data class PhoneNumber(val code: Int, val number: Int)

@Document
data class Table(val tableNumber: String, val status: String, val characteristics: TableCharacteristics)

@Document
data class TableCharacteristics(val type: String, val seatCapacity: Int, val infantSeat: Boolean)