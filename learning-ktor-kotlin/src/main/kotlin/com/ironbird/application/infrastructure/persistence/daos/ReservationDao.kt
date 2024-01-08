package com.ironbird.application.infrastructure.persistence.daos

import com.ironbird.application.infrastructure.persistence.daos.GuestDao.Companion.referrersOn
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.javatime.date


object Reservations: IntIdTable(name = "reservation", columnName = "reservation_id"){
    var guest = reference("guest_id", Guests)
    var room = reference("room_id", Rooms)
    var reservationDate = date(name = "res_date")
}
class ReservationDao(id: EntityID<Int>): IntEntity(id) {

    companion object : IntEntityClass<ReservationDao>(Reservations)

    var guest by GuestDao referencedOn Reservations.guest
    var room by RoomDao referencedOn Reservations.room
    var reservationDate by Reservations.reservationDate

    var roomName by Rooms.name


}