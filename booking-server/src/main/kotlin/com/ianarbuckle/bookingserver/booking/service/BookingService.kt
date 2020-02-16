package com.ianarbuckle.bookingserver.booking.service

import com.ianarbuckle.bookingserver.booking.model.Booking
import com.ianarbuckle.bookingserver.booking.repository.BookingRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 * @author ianarbuckle on 07/05/2019.
 */
interface BookingService {
    fun findAllBookings(): MutableList<Booking>
    fun saveBooking(booking: Booking)
    fun updateBooking(booking: Booking): Booking?
    fun findBookingsByUUID(uuid: String): MutableList<Booking>
    fun findBookingById(id: String): Booking
    fun deleteBookingById(id: String): Booking
}

@Service
class BookingServiceImpl : BookingService {

    @Autowired
    lateinit var repository: BookingRepository

    override fun findAllBookings(): MutableList<Booking> = repository.findAll()

    override fun findBookingById(id: String): Booking = repository.findBookingById(id)

    override fun findBookingsByUUID(uuid: String): MutableList<Booking> = repository.findBookingsByUUID(uuid)

    override fun saveBooking(booking: Booking) {
        repository.save(booking)
    }

    override fun updateBooking(booking: Booking): Booking? {
        return repository.save(booking).takeIf { repository.existsById(booking.id!!) }
    }

    override fun deleteBookingById(id: String): Booking {
        return repository.findBookingById(id).apply {
            repository.delete(this)
        }
    }
}