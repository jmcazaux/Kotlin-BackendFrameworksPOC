package com.ironbird.domain.data.entities

import kotlinx.serialization.Serializable

@Serializable
data class Room(
    var id: Long = 0,
    var name: String? = null,
    var roomNumber: String? = null,
    var bedInfo: String? = null,
) {
    override fun toString(): String {
        return "Room(id=$id, name=$name, roomNumber=$roomNumber, bedInfo=$bedInfo)"
    }
}
