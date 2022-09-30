package com.example.high.capacity.architecture

import java.util.LinkedList
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Component

@Component
class EventQueueComponent(
    private val redisTemplate: RedisTemplate<String, String>,
) {

    private lateinit var prizes: List<String>

    companion object {
        private val queue = LinkedList<String>()
        private const val KEY = "CAPACITY_TEST"
    }

    fun putUser(user: String) {
        queue.push(user)
    }

    fun lookUpStatus(id: String): Status {
        if (!this::prizes.isInitialized) {
            updatePrizes()
            return Status.WAIT
        }

        return if (prizes.contains(id)) {
            Status.PRIZE
        } else {
            Status.LOSE
        }
    }

    private fun updatePrizes() {
        val opsForList = redisTemplate.opsForList()
        val size = opsForList.size(KEY) ?: 0

        if (size >= 10000) {
            val range = opsForList.range(KEY, 0, 10000)
            prizes = range as List<String>
        }
    }


}