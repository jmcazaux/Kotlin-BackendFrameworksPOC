package com.ironbird.domain.usecases

import com.ironbird.domain.data.repositories.ReservationRepository
import java.time.LocalDate

class GetReservationsUseCase(private val reservationRepository: ReservationRepository) {

    fun getByDate(date: LocalDate?) = reservationRepository.getByDate(date)

}