package com.example.high.capacity.architecture

import java.lang.Exception
import java.util.Stack
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class LoadController {

    private final val queue: Stack<Int> = Stack()

    init {
        for (data in 1..10000) {
            queue.push(data)
        }
    }

    @GetMapping
    fun load(): String {
        if (queue.size == 0) {
            throw Exception("없엉")
        }

        return queue.pop().toString()
    }

}