package com.ianarbuckle.bookingserver

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.netflix.eureka.EnableEurekaClient

@SpringBootApplication
@EnableEurekaClient
class BookingServerApplication

fun main(args: Array<String>) {
	runApplication<BookingServerApplication>(*args)
}
