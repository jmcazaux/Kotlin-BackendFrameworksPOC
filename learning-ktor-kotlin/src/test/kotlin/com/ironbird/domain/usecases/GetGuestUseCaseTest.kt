package com.ironbird.domain.usecases

import com.ironbird.domain.data.entities.Guest
import com.ironbird.domain.data.repositories.GuestsRepository
import io.kotest.matchers.collections.shouldBeEmpty
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance

private class MockGuestsRepository(val withEmptyGuests: Boolean = false) : GuestsRepository {

    companion object {
        val GUESTS = listOf(
            Guest(id = 1, firstName = "John", lastName = "Doe", emailAddress = "john.doe@mail.com"),
            Guest(id = 2, firstName = "Jane", lastName = "Doe", emailAddress = "jane.doe@mail.com"),
            Guest(id = 3, firstName = "Joe", lastName = "Bloggs", emailAddress = "joe.bloggs@mail.com"),
        )
    }

    override fun getAll(): List<Guest> {
        if (this.withEmptyGuests) {
            return emptyList()
        }

        return GUESTS
    }
}

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class GetGuestUseCaseTest {

    private val guestsRepository = MockGuestsRepository()

    @Test
    fun `getAllGuests returns all guests when there are some`() {
        /*
         * Test setup
         */
        val useCase = GetGuestUseCase(this.guestsRepository)

        /*
         * Action
         */
        val allGuests = useCase.getAllGuests()

        /*
         * Assertions
         */
        allGuests shouldBe MockGuestsRepository.GUESTS

    }

    @Test
    fun `getAllGuests returns an empty list when there are no guests`() {
        /*
         * Test setup
         */
        val useCase = GetGuestUseCase(MockGuestsRepository(withEmptyGuests = true))

        /*
         * Action
         */
        val allGuests = useCase.getAllGuests()

        /*
         * Assertions
         */
        allGuests.shouldBeEmpty()
    }
}
