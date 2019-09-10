package com.ianarbuckle.restaurantlooker.booking.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

/**
 * @author ianarbuckle on 07/05/2019.
 *
 */

@Document
data class Booking(@Id val id: String? = null, val owner: Owner?, val restaurantName: String?, val table: Table?)

@Document
data class Owner(val uuid: String, val name: String, val email: String, val phoneNumber: Int, val dietaryRequirements: Boolean, val bookingDate: String, val arrivalTime: String)

@Document
data class Table(val tableNumber: String, val status: String, val characteristics: TableCharacteristics)

@Document
data class TableCharacteristics(val type: String, val seatCapacity: Int, val infantSeat: Boolean)