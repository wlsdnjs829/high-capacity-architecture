package com.example.high.capacity.architecture

import java.util.concurrent.ConcurrentLinkedQueue
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class EventService(
    private val eventRepository: EventRepository
) {

    companion object {
        private val queue = ConcurrentLinkedQueue<Long>()
    }

    @Transactional
    fun createEvent() {
        val event = Event(name = "JINWON", called = 0)
        eventRepository.save(event)
    }

    @Transactional
    fun callEvent(id: Long) {
        eventRepository.incrementCalledById(id)
        val event = findEvent(id)
        queue.add(event.called)
    }

    fun findEvent(id: Long): Event {
        return eventRepository.findById(id)
            .orElseThrow { throw Exception("이벤트 없음") }
    }

}