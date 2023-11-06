package com.ironbird.learningspring.util

import com.ironbird.learningspring.data.GuestRepository
import com.ironbird.learningspring.data.ReservationRepository
import com.ironbird.learningspring.data.RoomRepository
import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.context.ApplicationListener
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component
import java.sql.Date


@Component
class AppStartupEvent(
    val guestRepository: GuestRepository,
    val roomRepository: RoomRepository,
    val reservationRepository: ReservationRepository
) : ApplicationListener<ApplicationReadyEvent> {

    override fun onApplicationEvent(event: ApplicationReadyEvent) {
        println("************************************")
        println("***     Application started      ***")
        println("************************************")

        val entities = mapOf<String, CrudRepository<out Any, out Any>>(
            "guests" to guestRepository,
            "rooms" to roomRepository,
            "reservations" to reservationRepository
        )

        entities.forEach { (entity, repository) ->
            logEntity(entity, repository)
        }

        // Trying findById and findByRoom
        println("\n" + "*".repeat(80) + "\nFind a reservation by room")
        val room8 = roomRepository.findByIdOrNull(8)
        println("   Room 8: $room8")
        val roomReservation = reservationRepository.findByRoom(room8 ?: throw RuntimeException("Room not found"))
        println("   > Reservation ${roomReservation?.first()}")

        println("\n" + "*".repeat(80) + "\nFind a reservation by date")

        val reservationDate = Date.valueOf("2022-01-01")
        println("   Date: $reservationDate")
        val reservation = reservationRepository.findByReservationDate(reservationDate)
        reservation?.forEach { println("   > Reservation $it") }
    }

    private fun <T, K> logEntity(entity: String, repository: CrudRepository<out T, out K>) {
        println("\n >>> All $entity")
        val records = repository.findAll()
        records.forEach { println("   > $it") }
    }
}