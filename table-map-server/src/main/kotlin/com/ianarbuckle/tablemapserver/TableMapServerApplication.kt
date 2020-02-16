package com.ianarbuckle.tablemapserver

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class TableMapServerApplication

fun main(args: Array<String>) {
    runApplication<TableMapServerApplication>(*args)
}
