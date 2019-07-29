package com.ianarbuckle.restaurantlooker.tables.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

/**
 * @author ianarbuckle on 17/07/2019.
 */
@Document
data class Tables(@Id val restaurantId: String?, val restaurantName: String?, val row: MutableList<Row>?)

@Document
data class Row(val column: MutableList<Column>)

@Document
data class Column(val table: Table)

@Document
data class Table(val tableNumber: String, val status: String, val characteristics: TableCharacteristics)

@Document
data class TableCharacteristics(val type: String, val seatCapacity: Int, val infantSeat: Boolean)