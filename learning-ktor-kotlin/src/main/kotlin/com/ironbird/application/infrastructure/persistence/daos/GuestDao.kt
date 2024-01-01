package com.ironbird.application.infrastructure.persistence.daos

import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable


object Guests : IntIdTable(name = "guest", columnName = "guest_id") {
    var firstName = varchar("first_name", 64)
    var lastName = varchar("last_name", 64)
    var emailAddress = varchar("email_address", 64)
    var address = varchar("address", 64)
    var country = varchar("country", 64)
    var state = varchar("state", 64)
    var phoneNumber = varchar("phone_number", 64)
}

class GuestDao(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<GuestDao>(Guests)

    var firstName by Guests.firstName
    var lastName by Guests.lastName
    var emailAddress by Guests.emailAddress
    var address by Guests.address
    var country by Guests.country
    var state by Guests.state
    var phoneNumber by Guests.phoneNumber
}

