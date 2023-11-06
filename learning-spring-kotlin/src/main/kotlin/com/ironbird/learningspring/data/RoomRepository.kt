package com.ironbird.learningspring.data

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface RoomRepository:CrudRepository<Room, Long> {
}