package com.example.high.capacity.architecture

import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component

@Component
class LoadScheduler(
    private val eventQueueComponent: EventQueueComponent
) {

    @Scheduled(fixedDelay = 1000)
    fun migrationToRedis() {
        val tempUsers = ArrayList<String>()

        while (EventQueueComponent.queue.peek() != null) {
            EventQueueComponent.queue.poll()?.run {
                tempUsers.add(this)
            }
        }

        eventQueueComponent.addUsers(tempUsers)
    }

}