package com.ironbird.learningspring.data

import org.springframework.data.repository.CrudRepository
import java.sql.Date

interface ReservationRepository : CrudRepository<Reservation, Long> {
    fun findByReservationDate(reservationDate: Date): Iterable<Reservation?>?
    fun findByRoom(room: Room): Iterable<Reservation?>?
}
