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
            it.toGuest()
        }
    }
}

fun GuestDao.toGuest(): Guest {
    return Guest(firstName = this.firstName, lastName = this.lastName, emailAddress = this.emailAddress, address = this.address, country = this.country, state =  this.state, phoneNumber = this.phoneNumber)
}