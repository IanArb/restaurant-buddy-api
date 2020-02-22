package com.ianarbuckle.tablemapserver

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.netflix.eureka.EnableEurekaClient

@SpringBootApplication
@EnableEurekaClient
class TableMapServerApplication

fun main(args: Array<String>) {
    runApplication<TableMapServerApplication>(*args)
}
