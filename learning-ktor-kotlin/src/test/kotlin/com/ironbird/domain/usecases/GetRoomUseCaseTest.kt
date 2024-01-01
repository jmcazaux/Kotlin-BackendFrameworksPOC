package com.ironbird.domain.usecases

import com.ironbird.domain.data.entities.Room
import com.ironbird.domain.data.repositories.RoomsRepository
import io.kotest.matchers.collections.shouldBeEmpty
import io.kotest.matchers.collections.shouldNotBeEmpty
import io.kotest.matchers.nulls.shouldBeNull
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance


private class MockRoomRepository : RoomsRepository {

    companion object {
        val ROOMS = listOf(
            Room(1, "Best Room", "R1", "W1"),
            Room(2, "Better Room", "R2", "N2"),
        )
    }

    override fun getAll(): List<Room> {
        return ROOMS
    }

    override fun getByNumber(roomNumber: String): Room? {
        return ROOMS.singleOrNull { it.roomNumber == roomNumber }
    }

    override fun getByName(name: String): List<Room> {
        return ROOMS.filter { it.name == name }
    }
}

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class GetRoomUseCaseTest {

    private val roomsRepository = MockRoomRepository()


    @Test
    fun `getAll returns all rooms`() {
        val getRoomUseCase = GetRoomUseCase(this.roomsRepository)

        val allRooms = getRoomUseCase.getAll()
        allRooms shouldBe MockRoomRepository.ROOMS
    }

    @Test
    fun `getByNumber returns the expected room`() {
        val getRoomUseCase = GetRoomUseCase(this.roomsRepository)

        val roomR1 = getRoomUseCase.getByNumber("R1")
        roomR1.shouldNotBeNull()
        roomR1.roomNumber shouldBe "R1"

    }

    @Test
    fun `getByNumber returns Null when the room number does not exists`() {
        val getRoomUseCase = GetRoomUseCase(this.roomsRepository)

        val room = getRoomUseCase.getByNumber("NOT_A_ROOM")
        room.shouldBeNull()

    }

    @Test
    fun `getByName returns the expected rooms`() {
        val getRoomUseCase = GetRoomUseCase(this.roomsRepository)

        val bestRooms = getRoomUseCase.getByName("Best Room")
        bestRooms.shouldNotBeEmpty()
        bestRooms.first().name shouldBe "Best Room"
    }

    @Test
    fun `getByName returns empty list when the room name does not exists`() {
        val getRoomUseCase = GetRoomUseCase(this.roomsRepository)

        val rooms = getRoomUseCase.getByName("NOT_A_ROOM")
        rooms.shouldBeEmpty()
    }
}
