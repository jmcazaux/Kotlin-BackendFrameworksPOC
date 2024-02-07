package com.ironbird.learningquarkus.data.repository

import com.ironbird.learningquarkus.data.entity.Room
import io.quarkus.hibernate.orm.panache.PanacheRepository
import jakarta.enterprise.context.ApplicationScoped


@ApplicationScoped
class RoomRepository: PanacheRepository<Room> {
}
