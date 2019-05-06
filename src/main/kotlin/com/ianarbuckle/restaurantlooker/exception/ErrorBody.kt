package com.ianarbuckle.restaurantlooker.exception

import org.joda.time.LocalDateTime

/**
 * @author ianarbuckle on 02/09/2018.
 */
data class ErrorBody(private val timeStamp: LocalDateTime, private val message: String, private val details: String)