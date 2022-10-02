package com.example.high.capacity.architecture

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.Table

@Table
@Entity
class Event(

    @Id
    @GeneratedValue
    val id: Long = 0L,

    @Column(nullable = false, length = 128)
    val name: String,

    called: Long,

) {

    @Column(nullable = false)
    var called: Long = called
        protected set

    @Synchronized
    fun increment() {
        called += 1
    }

}