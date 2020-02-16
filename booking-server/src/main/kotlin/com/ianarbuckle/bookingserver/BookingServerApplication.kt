package com.ianarbuckle.bookingserver

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class BookingServerApplication

fun main(args: Array<String>) {
	runApplication<BookingServerApplication>(*args)
}
