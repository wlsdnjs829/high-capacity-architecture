package com.example.high.capacity.architecture

import java.util.Stack
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class LoadController(
    private val redisTemplate: RedisTemplate<String, String>,
) {

    companion object {
        private const val KEY = "CAPACITY_TEST"
    }

    init {
        val opsForValue = redisTemplate.opsForValue()
        opsForValue.set(KEY, "0")
    }

    @GetMapping
    fun load(): String {
        val opsForValue = redisTemplate.opsForValue()

        val data = opsForValue.get(KEY)?.toLong() ?: throw Exception("잘못된 타입")

        if (data > 10000L) {
            throw Exception("없엉")
        }

        val increment = opsForValue.increment(KEY) ?: throw Exception("잘못된 타입")

        if (increment > 10000L) {
            throw Exception("없엉")
        }

        return increment.toString()
    }

}