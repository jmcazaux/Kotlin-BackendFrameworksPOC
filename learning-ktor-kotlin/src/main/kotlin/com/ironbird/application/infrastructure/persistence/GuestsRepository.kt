package com.ironbird.application.infrastructure.persistence

import com.ironbird.application.infrastructure.persistence.daos.GuestDao
import com.ironbird.domain.data.entities.Guest
import org.jetbrains.exposed.sql.transactions.transaction
import com.ironbird.domain.data.repositories.GuestsRepository as GuestsRepositoryInterface

class GuestsRepository : GuestsRepositoryInterface {
    override fun getAll(): List<Guest> {
        val resultSet = transaction {
            GuestDao.all().sortedBy { guest -> "${guest.lastName} ${guest.firstName}" }
        }

        return resultSet.map {
            Guest(firstName = it.firstName, lastName = it.lastName, emailAddress = it.emailAddress, address = it.address, country = it.country, state =  it.state, phoneNumber = it.phoneNumber)
        }
    }
}
