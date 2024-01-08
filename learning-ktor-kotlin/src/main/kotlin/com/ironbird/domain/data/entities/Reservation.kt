package com.ironbird.domain.data.entities

import com.ironbird.application.infrastructure.persistence.daos.Reservations.guest
import com.ironbird.application.infrastructure.persistence.daos.Reservations.reservationDate
import com.ironbird.application.infrastructure.persistence.daos.Reservations.room
import java.time.LocalDate
import java.util.*

data class Reservation(
        var reservationId: Int = 0,
        var room: Room,
        var guest: Guest? = null,
        var reservationDate: LocalDate? = null
){
        override fun toString(): String {
                return "Reservation(reservationId=$reservationId, room=$room, guest=$guest, reservationDate=$reservationDate)"
        }


}