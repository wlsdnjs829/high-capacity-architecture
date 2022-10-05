package com.example.high.capacity.architecture

import java.util.Optional
import javax.persistence.LockModeType
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Lock
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface EventRepository : JpaRepository<Event, Long> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    override fun findById(id: Long): Optional<Event>

    @Modifying
    @Query(value = "UPDATE Event SET called = called + 1 WHERE id = :id")
    fun incrementCalledById(id: Long)

}