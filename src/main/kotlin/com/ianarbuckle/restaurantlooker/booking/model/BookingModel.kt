package com.ianarbuckle.restaurantlooker.booking.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

/**
 * @author ianarbuckle on 07/05/2019.
 *
 */

@Document
data class Booking(@Id val id: String? = null, val owner: Owner?, val details: RestaurantDetails, val table: Table?)

@Document
data class RestaurantDetails(val name: String, val address: String, val location: Location)

data class Location(val latitude: Float, val longitude: Float)

@Document
data class Owner(val uuid: String, val name: String, val phoneNumber: Int, val dietaryRequirements: Boolean, val arrivalTime: String)

@Document
data class Table(val tableNumber: String, val status: String, val characteristics: TableCharacteristics)

@Document
data class TableCharacteristics(val type: String, val seatCapacity: Int, val infantSeat: Boolean)