package com.ironbird.domain.usecases

import com.ironbird.domain.data.entities.Guest
import com.ironbird.domain.data.repositories.GuestsRepository

class GetGuestUseCase(private val guestsRepository: GuestsRepository) {

    fun getAllGuests(): List<Guest> {
        return guestsRepository.getAll()
    }
}
