package com.example.high.capacity.architecture

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableScheduling

@EnableScheduling
@SpringBootApplication
class HighCapacityArchitectureApplication

fun main(args: Array<String>) {
    runApplication<HighCapacityArchitectureApplication>(*args)
}
