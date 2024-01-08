package com.ironbird.domain.data.repositories

import com.ironbird.domain.data.entities.Reservation
import java.time.LocalDate

interface ReservationRepository {

    fun getByDate(date: LocalDate?): List<Reservation>
}