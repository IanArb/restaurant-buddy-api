package com.ianarbuckle.bookingserver.booking.controller

import com.ianarbuckle.bookingserver.booking.model.Booking
import com.ianarbuckle.bookingserver.booking.service.BookingService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

/**
 * @author ianarbuckle on 07/05/2019.
 */
@RequestMapping("/booking")
@RestController
class BookingController {

    @Autowired
    lateinit var service: BookingService

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    fun retrieveBookings(): MutableList<Booking> = service.findAllBookings()

    @GetMapping("{uuid}")
    @ResponseStatus(HttpStatus.OK)
    fun retrieveBookingsByUUID(@PathVariable uuid: String): MutableList<Booking> = service.findBookingsByUUID(uuid)

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createBooking(@RequestBody booking: Booking) = service.saveBooking(booking)

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    fun deleteBookingById(@PathVariable id: String) = service.deleteBookingById(id)

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    fun updateBooking(@RequestBody booking: Booking) = service.updateBooking(booking)

}