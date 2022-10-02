package com.example.high.capacity.architecture

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface EventRepository : JpaRepository<Event, Long> {

    @Modifying
    @Query(value = "UPDATE Event SET called = called + 1 WHERE id = :id")
    fun incrementCalledById(id: Long)

}