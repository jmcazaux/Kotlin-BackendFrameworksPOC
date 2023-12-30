package com.ironbird.domain.data.repositories

import com.ironbird.domain.data.entities.Room

interface RoomsRepository {

    fun getAll(): List<Room>

    fun getByNumber(roomNumber: String): Room?

    fun getByName(name: String): List<Room>

}
