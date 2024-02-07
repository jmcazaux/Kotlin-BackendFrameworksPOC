package com.ironbird.learningquarkus.webservice

import com.ironbird.learningquarkus.data.entity.Guest
import com.ironbird.learningquarkus.data.entity.Room
import com.ironbird.learningquarkus.data.repository.GuestRepository
import com.ironbird.learningquarkus.data.repository.RoomRepository
import jakarta.transaction.Transactional
import jakarta.ws.rs.*
import org.eclipse.microprofile.openapi.annotations.Operation
import org.eclipse.microprofile.openapi.annotations.media.Content
import org.eclipse.microprofile.openapi.annotations.media.Schema
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses
import org.eclipse.microprofile.openapi.annotations.tags.Tag


@Schema(name="Room (create)", description="Representation of the room to create")
class RoomCreate(private val name: String, private val roomNumber: String, private val bedInfo: String) {

    fun toRoom(): Room {
        return Room(
            name = name,
            roomNumber = roomNumber,
            bedInfo = bedInfo
        )
    }
}

@Path("/api")
@Produces("application/json")
@Consumes("application/json")
class WebServiceController(
    private val guestRepository: GuestRepository,
    private val roomRepository: RoomRepository
) {
    @GET
    @Path("/guests")
    @Operation(
        summary = "List all guests",
        description = "Returns an array containing all guests (possibly empty).")
    @APIResponses(value = [
        APIResponse(responseCode = "200", description = "Success"),
        APIResponse(responseCode = "500", description="An unexpected error happened")
    ])
    @Tag(name="Guests")
    fun getGuests(): List<Guest> {
        return guestRepository.allGuests()
    }

    @GET
    @Path("/rooms")
    @Operation(
        summary = "List all rooms",
        description = "Returns an array containing all rooms (possibly empty).")
    @APIResponses(value = [
        APIResponse(responseCode = "200", description = "Success"),
        APIResponse(responseCode = "500", description="An unexpected error happened")
    ])
    @Tag(name="Rooms")
    fun getRooms(): List<Room> {
        return roomRepository.listAll()
    }

    @POST
    @Path("/rooms")
    @Transactional
    @Operation(
        summary = "Create a room",
        description = "Create a room from the request payload and returns it.")
    @APIResponses(value = [
        APIResponse(
            responseCode = "200",
            description = "Success",
            content = arrayOf(Content(mediaType = "application/json", schema = Schema(name="Room", implementation = Room::class)))
        ),
        APIResponse(responseCode = "400", description="Bad request or malformed payload"),
        APIResponse(responseCode = "500", description="An error happened")
    ])
    @Tag(name="Rooms")
    fun createRoom(roomToCreate: RoomCreate) : Room {
        val room = roomToCreate.toRoom()
        roomRepository.persistAndFlush(room)
        return room
    }
}
