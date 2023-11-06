package com.ironbird.learningspring.data

import jakarta.persistence.*


@Entity
@Table(name = "ROOM")
class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ROOM_ID")
    var roomId: Long = 0

    @Column(name = "NAME")
    var name: String? = null

    @Column(name = "ROOM_NUMBER")
    var roomNumber: String? = null

    @Column(name = "BED_INFO")
    var bedInfo: String? = null
    override fun toString(): String {
        return "Room(roomId=$roomId, name=$name, roomNumber=$roomNumber, bedInfo=$bedInfo)"
    }


}