package com.ironbird.learningspring.webservice

import com.ironbird.learningspring.business.ReservationService
import com.ironbird.learningspring.business.RoomReservation
import com.ironbird.learningspring.data.Guest
import com.ironbird.learningspring.data.Room
import com.ironbird.learningspring.util.DateUtils
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api")
class WebserviceController(private val dateUtils: DateUtils, private val reservationService: ReservationService) {

    @GetMapping(path = ["/reservations"])
    fun getReservations(@RequestParam(value = "date", required = false) dateParam: String?): List<RoomReservation> {
        val date = dateUtils.createDateFromDateString(dateParam)
        return reservationService.getRoomReservationsForDate(date)
    }

    @GetMapping(path = ["/guests"])
    fun getGuests(): List<Guest> {
        return reservationService.getHotelGuests()
    }

    @PostMapping(path = ["/guests"])
    fun addGuest(@RequestBody guest: Guest) {
        reservationService.addHotelGuest(guest)
    }

    @GetMapping(path = ["/rooms"])
    fun getRooms(): List<Room> {
        return reservationService.getRooms()
    }
}