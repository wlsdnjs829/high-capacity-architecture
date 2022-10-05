package com.example.high.capacity.architecture

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(value = ["/event"])
class EventController(
    private val eventService: EventService,
) {

    @GetMapping("/{id}")
    fun findEvent(@PathVariable id: Long): Event {
        return eventService.findEvent(id)
    }

    @PostMapping
    fun createEvent() {
        return eventService.createEvent()
    }

    @PostMapping("/{id}")
    fun callEvent(@PathVariable id: Long) {
        return eventService.callEventTest(id)
    }

}