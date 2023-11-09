package com.ironbird.learningspring.business

import com.ironbird.learningspring.data.*
import org.springframework.stereotype.Service
import java.util.*


@Service
class ReservationService(
    private val reservationRepository: ReservationRepository,
    private val roomRepository: RoomRepository,
    private val guestRepository: GuestRepository
) {

    fun getRoomReservationsForDate(date: Date): List<RoomReservation> {
        val rooms = roomRepository.findAll()

        val roomReservationMap = rooms.associate { room ->
            room.roomId to RoomReservation(
                roomId = room.roomId,
                roomName = room.name,
                roomNumber = room.roomNumber
            )
        }

        val reservations = reservationRepository.findByReservationDate(java.sql.Date(date.time))
        reservations?.forEach {
            val roomReservation = roomReservationMap[it?.room?.roomId]
            roomReservation?.date = it?.reservationDate
            roomReservation?.guestId = it?.guest?.guestId
            roomReservation?.firstName = it?.guest?.firstName
            roomReservation?.lastName = it?.guest?.lastName
        }

        val reservationsForDate = roomReservationMap.flatMap { (_, roomReservation) -> listOf(roomReservation) }


        return reservationsForDate.sortedBy { it.roomNumber }
    }

    fun getHotelGuests(): List<Guest> {
        val guests = guestRepository.findAll().toList()

        return guests.sortedWith { g1, g2 ->
            if (g1.lastName == null || g1.lastName == g2.lastName) {
                g1.firstName?.compareTo(g2.firstName ?: "") ?: -1
            } else {
                g1.lastName.compareTo(g2.lastName ?: "")
            }
        }
    }

    fun addHotelGuest(guest: Guest?) {
        if (guest == null) {
            throw RuntimeException("Cannot add a null Guest")
        }
        guestRepository.save(guest)
    }


    fun getRooms(): List<Room> {
        val rooms = roomRepository.findAll().toList()

        return rooms.sortedBy { room -> room.roomNumber }
    }
}