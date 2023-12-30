package com.ironbird.application.repositories


import io.kotest.matchers.collections.shouldBeEmpty
import io.kotest.matchers.collections.shouldBeSortedBy
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.nulls.shouldBeNull
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test


import org.junit.jupiter.api.TestInstance

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class RoomsRepositoryTest : TestWithDb() {
    
    @Test
    fun `getAll returns all rooms when some room exists`() {
        this.resetDb()
        this.prefillDb()
        val allRooms = RoomsRepository().getAll()
        allRooms shouldHaveSize 4
        allRooms shouldBeSortedBy { it.roomNumber ?: "" }
    }

    @Test
    fun `getAll returns empty list when no room exists`() {
        val allRooms = RoomsRepository().getAll()
        allRooms.shouldBeEmpty()

    }

    @Test
    fun `getByNumber returns the expected room`() {
        this.prefillDb()
        val roomR1 = RoomsRepository().getByNumber("R1")
        roomR1.shouldNotBeNull()

        roomR1.roomNumber shouldBe "R1"
    }

    @Test
    fun `getByNumber returns Null when the room number does not exists`() {
        this.prefillDb()
        val room = RoomsRepository().getByNumber("NO_ROOM")
        room.shouldBeNull()
    }

    @Test
    fun `getByName returns the expected rooms`() {
        this.prefillDb()
        val roomsInL1 = RoomsRepository().getByName("Location 1")
        roomsInL1 shouldHaveSize 2

        roomsInL1 should { rooms -> rooms.forEach { it.name shouldBe "Location 1" } }
    }


    @Test
    fun `getByName returns none when the room name does not exists`() {
        this.prefillDb()
        val roomsInL1 = RoomsRepository().getByName("NOT_A_LOCATION")
        roomsInL1.shouldBeEmpty()
    }
}
