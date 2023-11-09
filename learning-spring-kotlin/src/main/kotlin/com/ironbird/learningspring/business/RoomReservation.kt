package com.ironbird.learningspring.business

import java.util.*

data class RoomReservation(
    var roomId: Long?,
    var guestId: Long? = null,  // Does not really make sense until you look into ReservationService.getRoomReservationsForDate
    var roomName: String?,
    var roomNumber: String?,
    var firstName: String? = null,
    var lastName: String? = null,
    var date: Date? = null
)
