package com.ironbird.application.infrastructure.persistence

import com.ironbird.application.infrastructure.persistence.daos.ReservationDao
import com.ironbird.application.infrastructure.persistence.daos.Reservations.reservationDate
import com.ironbird.application.infrastructure.persistence.daos.RoomDao
import com.ironbird.domain.data.entities.Reservation
import org.jetbrains.exposed.sql.transactions.transaction
import java.time.LocalDate
import com.ironbird.domain.data.repositories.ReservationRepository as ReservationRepositoryInterface


class ReservationRepository : ReservationRepositoryInterface {
    override fun getByDate(date: LocalDate?): List<Reservation> {
        val actualDate = date ?: LocalDate.now()

        val roomReservations = transaction {
            RoomDao.all()
                .map { it.toRoom() }
                .associate { room ->
                    room.id to Reservation(
                        room = room,
                    )
                }
        }

        val reservations = transaction {
            ReservationDao.find { reservationDate.eq(actualDate) }
                .map {
                    Reservation(
                        reservationId = it.id.value,
                        reservationDate = it.reservationDate,
                        room = it.room.toRoom(),
                        guest = it.guest.toGuest()
                    )
                }
        }

        reservations.forEach {
            roomReservations[it.room.id]?.reservationId = it.reservationId
            roomReservations[it.room.id]?.reservationDate = it.reservationDate
            roomReservations[it.room.id]?.guest = it.guest
        }

        return roomReservations.flatMap { (_, reservation) -> listOf(reservation) }
    }
}
