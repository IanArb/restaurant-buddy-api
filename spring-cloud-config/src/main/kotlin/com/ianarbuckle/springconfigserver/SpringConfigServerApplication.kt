package com.ianarbuckle.springconfigserver

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.config.server.EnableConfigServer

@EnableConfigServer
@SpringBootApplication
class SpringConfigServerApplication

fun main(args: Array<String>) {
	runApplication<SpringConfigServerApplication>(*args)
}
