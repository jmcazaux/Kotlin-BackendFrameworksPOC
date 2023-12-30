package com.ironbird.domain.usecases

import com.ironbird.domain.data.entities.Room
import com.ironbird.domain.data.repositories.RoomsRepository


class GetRoomUseCase(private val roomsRepository: RoomsRepository) {

    fun getAll(): List<Room> {
        return roomsRepository.getAll()
    }

    fun getByNumber(roomNumber: String): Room? {
        return roomsRepository.getByNumber(roomNumber)
    }

    fun getByName(name: String): List<Room> {
        return roomsRepository.getByName(name)
    }
}
