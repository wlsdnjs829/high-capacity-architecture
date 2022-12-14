package com.example.high.capacity.architecture

import java.util.concurrent.ThreadLocalRandom
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class LoadController(
    private val eventQueueComponent: EventQueueComponent,
) {

    @GetMapping
    fun load(): String {
        eventQueueComponent.putUser(ThreadLocalRandom.current().nextInt().toString())
        return "등록 완료"
    }

    @GetMapping("/look-up/{id}")
    fun lookUp(@PathVariable id: String): Status {
        return eventQueueComponent.lookUpStatus(id)
    }

}