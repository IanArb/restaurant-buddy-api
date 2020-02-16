package com.ianarbuckle.restaurantlooker.utils

import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.databind.JsonSerializer
import com.fasterxml.jackson.databind.SerializerProvider
import org.joda.time.DateTime
import org.joda.time.format.DateTimeFormat
import org.joda.time.format.DateTimeFormatter

/**
 * @author ianarbuckle on 10/05/2019.
 */
class DateTimeSerializer : JsonSerializer<DateTime>() {

    private val formatter: DateTimeFormatter = DateTimeFormat.mediumDate()

    override fun serialize(value: DateTime?, gen: JsonGenerator?, serializers: SerializerProvider?) {
        gen?.writeString(formatter.print(value))
    }
}