package com.ianarbuckle.bookingserver.utils

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import com.ianarbuckle.bookingserver.booking.model.*
import java.time.LocalDateTime
import java.time.Month


/**
 * @author ianarbuckle on 28/05/2018.
 */
object TestUtils {

    fun buildBookingsModel(): MutableList<Booking> {
        val bookings = mutableListOf<Booking>()

        bookings.add(createBooking())

        return bookings
    }

    fun createBooking(): Booking {
        val dateTime = LocalDateTime.of(2020, Month.JANUARY, 1, 10, 10, 30)

        val owner = Owner("24345-34534-34534", "John Doe", "iarbuckle@mail.com", PhoneNumber(353, 1234567890), false, "12/12/2020", "17:00")
        val details = RestaurantDetails("Crillo's", "imageUrl", "address", Location(0.0f, 0.0f))
        return Booking("1", owner, details, Table("14", "RESERVED", TableCharacteristics("FAMILY", 8, true)))
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