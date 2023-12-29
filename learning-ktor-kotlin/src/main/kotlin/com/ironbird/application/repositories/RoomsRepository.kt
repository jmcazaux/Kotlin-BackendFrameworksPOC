package com.ironbird.application.repositories

import com.ironbird.domain.data.entities.Room
import org.jetbrains.exposed.sql.Table
import com.ironbird.domain.data.repositories.RoomsRepository as RoomsRepositoryInterface




class RoomsRepository : RoomsRepositoryInterface {

    object Rooms : Table(name="room") {
        var id = long("room_id").autoIncrement()
        var name = varchar("name", length=256)
        var roomNumber = varchar("room_number", length=16)
        var bedInfo = varchar("bed_info", length=64)

        override val primaryKey = PrimaryKey(id)
    }

    override fun getAll(): List<Room> {
        TODO("Not yet implemented")
    }

    override fun getByNumber(roomNumber: String): Room? {
        TODO("Not yet implemented")
    }

    override fun getByName(roomName: String): Room? {
        TODO("Not yet implemented")
    }
}
