package com.example.high.capacity.architecture

import java.util.Queue
import java.util.concurrent.ConcurrentLinkedQueue
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Component

@Component
class EventQueueComponent(
    private val redisTemplate: RedisTemplate<String, String>,
) {

    private lateinit var prizes: List<String>

    companion object {
        val queue: Queue<String> = ConcurrentLinkedQueue()
        private const val KEY = "CONCURRENT"
    }

    fun putUser(user: String) {
        queue.add(user)
    }

    fun lookUpStatus(id: String): Status {
        return if (!this::prizes.isInitialized) {
            updatePrizes()
            return Status.WAIT
        } else if (prizes.contains(id)) {
            Status.PRIZE
        } else {
            Status.LOSE
        }
    }

    fun addUsers(users: List<String>) {
        if (users.isNotEmpty()) {
            println(users[0])
            val opsForList = redisTemplate.opsForList()
            opsForList.rightPushAll(KEY, users)
        }
    }

    private fun updatePrizes() {
        val opsForList = redisTemplate.opsForList()
        val size = opsForList.size(KEY) ?: 0

        if (size >= 10000) {
            val range = opsForList.range(KEY, 0, 9999)
            prizes = range as List<String>
        }
    }

}