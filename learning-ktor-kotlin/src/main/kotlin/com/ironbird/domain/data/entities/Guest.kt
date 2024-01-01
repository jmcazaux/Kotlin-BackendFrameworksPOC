package com.ironbird.domain.data.entities

class Guest(
    var id: Int = 0,
    val firstName: String? = null,
    val lastName: String? = null,
    val emailAddress: String? = null,
    val address: String? = null,
    val country: String? = null,
    val state: String? = null,
    val phoneNumber: String? = null
) {
    override fun toString(): String {
        return "Guest(id=$id, firstName=$firstName, lastName=$lastName, emailAddress=$emailAddress, address=$address, country=$country, state=$state, phoneNumber=$phoneNumber)"
    }
}
