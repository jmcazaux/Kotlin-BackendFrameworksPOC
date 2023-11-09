package com.ironbird.learningspring.data

import jakarta.persistence.*

@Entity
@Table(name = "GUEST")
class Guest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "GUEST_ID", nullable = false)
    var guestId: Long? = null

    @Column(name = "FIRST_NAME")
    val firstName: String? = null

    @Column(name = "LAST_NAME")
    val lastName: String? = null

    @Column(name = "EMAIL_ADDRESS")
    val emailAddress: String? = null

    @Column(name = "ADDRESS")
    val address: String? = null

    @Column(name = "COUNTRY")
    val country: String? = null

    @Column(name = "STATE")
    val state: String? = null

    @Column(name = "PHONE_NUMBER")
    val phoneNumber: String? = null

    override fun toString(): String {
        return "Guest(guestId=$guestId, firstName=$firstName, lastName=$lastName, emailAddress=$emailAddress, address=$address, country=$country, state=$state, phoneNumber=$phoneNumber)"
    }
}
