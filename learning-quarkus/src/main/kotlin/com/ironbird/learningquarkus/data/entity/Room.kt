package com.ironbird.learningquarkus.data.entity

import jakarta.persistence.*


@Entity
@Table(name = "ROOM")
class Room(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ROOM_ID") var roomId: Long? = null,
    @Column(name = "NAME") var name: String? = null,
    @Column(name = "ROOM_NUMBER") var roomNumber: String? = null,
    @Column(name = "BED_INFO") var bedInfo: String? = null
) {

    override fun toString(): String {
        return "Room(roomId=$roomId, name=$name, roomNumber=$roomNumber, bedInfo=$bedInfo)"
    }
}
