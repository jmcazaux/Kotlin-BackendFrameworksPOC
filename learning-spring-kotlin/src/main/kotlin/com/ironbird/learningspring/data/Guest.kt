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
    private val firstName: String? = null

    @Column(name = "LAST_NAME")
    private val lastName: String? = null

    @Column(name = "EMAIL_ADDRESS")
    private val emailAddress: String? = null

    @Column(name = "ADDRESS")
    private val address: String? = null

    @Column(name = "COUNTRY")
    private val country: String? = null

    @Column(name = "STATE")
    private val state: String? = null

    @Column(name = "PHONE_NUMBER")
    private val phoneNumber: String? = null

    override fun toString(): String {
        return "Guest(guestId=$guestId, firstName=$firstName, lastName=$lastName, emailAddress=$emailAddress, address=$address, country=$country, state=$state, phoneNumber=$phoneNumber)"
    }


}
