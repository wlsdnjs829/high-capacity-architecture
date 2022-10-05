package com.example.high.capacity.architecture

import java.util.concurrent.ConcurrentLinkedQueue
import javax.persistence.LockModeType
import org.springframework.data.jpa.repository.Lock
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Isolation
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

    @Transactional(isolation = Isolation.SERIALIZABLE)
    fun callEventTest(id: Long) {
        val event = findEvent(id)
        event.increment()
        val save = eventRepository.save(event)
        println(save.called)
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