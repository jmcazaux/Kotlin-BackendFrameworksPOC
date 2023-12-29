package com.ironbird.domain.usecases

import com.ironbird.domain.data.entities.Room
import com.ironbird.domain.data.repositories.RoomsRepository
import org.junit.Assert
import org.junit.Test
import org.junit.jupiter.api.TestInstance


class TestRoomRepository : RoomsRepository {

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

    override fun getByName(roomName: String): Room? {
        return ROOMS.singleOrNull { it.name == roomName }
    }
}

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class GetRoomUseCaseTest {

    @Test
    fun `getAll returns all rooms`() {
        val getRoomUseCase = GetRoomUseCase(TestRoomRepository())

        val allRooms = getRoomUseCase.getAll()
        Assert.assertEquals(TestRoomRepository.ROOMS.size, allRooms.size)
        Assert.assertSame(TestRoomRepository.ROOMS, allRooms)
    }

    @Test
    fun `getByNumber returns the expected room`() {
        val getRoomUseCase = GetRoomUseCase(TestRoomRepository())

        val roomR1 = getRoomUseCase.getByNumber("R1")
        Assert.assertNotNull(roomR1)
        Assert.assertEquals("R1", roomR1?.roomNumber)

    }

    @Test
    fun `getByNumber returns Null when the room number does not exists`() {
        val getRoomUseCase = GetRoomUseCase(TestRoomRepository())

        val room = getRoomUseCase.getByNumber("NOT_A_ROOM")
        Assert.assertNull(room)

    }

    @Test
    fun `getByName returns the expected room`() {
        val getRoomUseCase = GetRoomUseCase(TestRoomRepository())

        val bestRoom = getRoomUseCase.getByName("Best Room")
        Assert.assertNotNull(bestRoom)
        Assert.assertEquals("Best Room", bestRoom?.name)
    }

    @Test
    fun `getByName returns none when the room name does not exists`() {
        val getRoomUseCase = GetRoomUseCase(TestRoomRepository())

        val room = getRoomUseCase.getByName("NOT_A_ROOM")
        Assert.assertNull(room)
    }
}
