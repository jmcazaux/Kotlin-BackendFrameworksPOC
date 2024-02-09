package com.ironbird.learningquarkus.data.repository

import com.ironbird.learningquarkus.data.entity.Guest
import io.quarkus.hibernate.orm.panache.kotlin.PanacheRepository
import jakarta.enterprise.context.ApplicationScoped

@ApplicationScoped
class GuestRepository : PanacheRepository<Guest> {
    fun allGuests(): List<Guest> = listAll()
}
