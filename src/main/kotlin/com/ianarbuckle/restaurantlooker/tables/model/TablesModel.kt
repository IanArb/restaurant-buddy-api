package com.ianarbuckle.restaurantlooker.tables.model

import io.swagger.annotations.ApiModel
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import javax.validation.constraints.NotEmpty

/**
 * @author ianarbuckle on 17/07/2019.
 */
@ApiModel(description = "Table reservation details")
@Document
data class Tables(@Id val restaurantId: String?,
                  @NotEmpty
                  val restaurantName: String?,
                  @NotEmpty
                  val row: MutableList<Row>?)

@Document
data class Row(val column: MutableList<Column>)

@Document
data class Column(val table: Table)

@Document
data class Table(val tableNumber: String, val status: String, val characteristics: TableCharacteristics)

@Document
data class TableCharacteristics(val type: String, val seatCapacity: Int, val infantSeat: Boolean)