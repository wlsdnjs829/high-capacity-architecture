package com.example.high.capacity.architecture

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class LoadController {

    @GetMapping
    fun load(): String {
        println("logging")
        return "JINWON"
    }

}