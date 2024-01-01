package com.ironbird.application.infrastructure.persistence

import com.ironbird.application.infrastructure.persistence.daos.RoomDao
import com.ironbird.application.infrastructure.persistence.daos.Rooms
import com.ironbird.domain.data.entities.Room
import org.jetbrains.exposed.sql.transactions.transaction
import com.ironbird.domain.data.repositories.RoomsRepository as RoomsRepositoryInterface


class RoomsRepository : RoomsRepositoryInterface {

    override fun getAll(): List<Room> {
        val resultSet = transaction {
            RoomDao.all().sortedBy { it.roomNumber }
        }
        return resultSet.map {
            it.toRoom()
        }
    }

    override fun getByNumber(roomNumber: String): Room? {
        val roomDao = transaction {
            RoomDao.find { Rooms.roomNumber eq roomNumber }.firstOrNull()
        }
        return roomDao?.toRoom()
    }

    override fun getByName(name: String): List<Room> {
        val resultSet = transaction {
            RoomDao.find { Rooms.name eq name }.sortedBy { it.roomNumber }
        }
        return resultSet.map {
            it.toRoom()
        }
    }
}


fun RoomDao.toRoom(): Room {
    return Room(id = this.id.value, name = this.name, bedInfo = this.bedInfo, roomNumber = this.roomNumber)
}
