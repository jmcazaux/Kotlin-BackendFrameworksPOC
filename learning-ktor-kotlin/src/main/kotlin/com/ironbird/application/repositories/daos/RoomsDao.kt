package com.ironbird.application.repositories.daos

import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable

object Rooms : IntIdTable(name = "room", columnName = "room_id") {
    var name = varchar("name", length = 256)
    var roomNumber = varchar("room_number", length = 16)
    var bedInfo = varchar("bed_info", length = 64)
}

class RoomDao(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<RoomDao>(Rooms)

    var name by Rooms.name
    var roomNumber by Rooms.roomNumber
    var bedInfo by Rooms.bedInfo

}
