package com.ianarbuckle.restaurantlooker.booking.model

import org.joda.time.LocalDateTime
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

/**
 * @author ianarbuckle on 07/05/2019.
 *
 */

@Document
data class Booking(@Id val id: String? = null, val owner: Owner?, val restaurantName: String?, val table: Table?)

@Document
data class Owner(val uuid: String, val name: String, val phoneNumber: Int)

@Document
data class Table(val tableType: String, val numberOfCustomers: Int, val dietaryRequirements: Boolean, val infant: Boolean, val arrivalTime: LocalDateTime)