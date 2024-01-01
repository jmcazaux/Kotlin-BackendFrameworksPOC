package com.ironbird.domain.data.repositories

import com.ironbird.domain.data.entities.Guest

interface GuestsRepository {

    fun getAll(): List<Guest>
}
