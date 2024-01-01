package com.ironbird.application.infrastructure.persistence

import com.ironbird.application.TestWithDb
import io.kotest.matchers.collections.shouldBeSortedBy
import io.kotest.matchers.collections.shouldHaveSize
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class GuestsRepositoryTest : TestWithDb() {

    @Test
    fun getAll() {
        /*
         * Test setup
         */
        prefillDb()

        val repository = GuestsRepository()

        /*
         * Action
         */
        val allGuests = repository.getAll()

        /*
         * Assertions
         */
        allGuests shouldHaveSize 6
        allGuests shouldBeSortedBy { guest -> guest.lastName + guest.firstName }
    }
}
