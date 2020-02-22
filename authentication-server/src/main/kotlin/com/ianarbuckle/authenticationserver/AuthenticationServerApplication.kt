package com.ianarbuckle.authenticationserver

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.netflix.eureka.EnableEurekaClient

@SpringBootApplication
@EnableEurekaClient
class AuthenticationServerApplication

fun main(args: Array<String>) {
    runApplication<AuthenticationServerApplication>(*args)
}
