package com.ianarbuckle.tablemapserver.utils

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import com.ianarbuckle.tablemapserver.tables.model.*


/**
 * @author ianarbuckle on 28/05/2018.
 */
object TestUtils {

    fun createTables(): Tables {
        val rows = ArrayList<Row>()
        val columns = ArrayList<Column>()
        val table = Table("1", "AVAILABLE", TableCharacteristics("FAMILY", 4, false))
        val column = Column(table)
        val row = Row(columns)

        columns.add(column)
        rows.add(row)

        return Tables("1", "Buckle's", rows)
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