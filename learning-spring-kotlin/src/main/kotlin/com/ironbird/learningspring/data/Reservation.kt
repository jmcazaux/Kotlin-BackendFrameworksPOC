package com.ironbird.learningspring.data

import jakarta.persistence.*
import java.util.*

@Entity
@Table(name = "RESERVATION")
class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "RESERVATION_ID")
    var reservationId: Long = 0

    @ManyToOne
    @JoinColumn(name = "ROOM_ID", referencedColumnName = "ROOM_ID")
    var room: Room? = null

    @ManyToOne
    @JoinColumn(name = "GUEST_ID", referencedColumnName = "GUEST_ID")
    var guest: Guest? = null

    @Column(name = "RES_DATE")
    var reservationDate: Date? = null

    override fun toString(): String {
        return "Reservation(reservationId=$reservationId, room=$room, guest=$guest, reservationDate=$reservationDate)"
    }
}
