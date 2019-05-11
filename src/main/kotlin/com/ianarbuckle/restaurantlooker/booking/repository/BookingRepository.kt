package com.ianarbuckle.restaurantlooker.booking.repository

import com.ianarbuckle.restaurantlooker.booking.model.Booking
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.data.mongodb.repository.Query
import org.springframework.stereotype.Repository

/**
 * @author ianarbuckle on 07/05/2019.
 */
@Repository
interface BookingRepository: MongoRepository<Booking, String> {
    @Query("{'owner.uuid': ?0}")
    fun findBookingsByUUID(uuid: String): MutableList<Booking>
    fun findBookingById(id: String): Booking
}