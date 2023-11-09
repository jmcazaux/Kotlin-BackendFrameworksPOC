package com.ironbird.learningspring.web

import com.ironbird.learningspring.business.ReservationService
import com.ironbird.learningspring.util.DateUtils
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam

@Controller
@RequestMapping("/reservations")
class RoomReservationController(private val dateUtils: DateUtils, private val reservationService: ReservationService) {

    @RequestMapping(method = [RequestMethod.GET])
    fun getReservation(
        @RequestParam(value = "date", required = false) reservationDateParam: String?,
        model: Model
    ): String {
        val reservationDate = dateUtils.createDateFromDateString(reservationDateParam)
        val roomReservations = reservationService.getRoomReservationsForDate(reservationDate)

        model.addAttribute("roomReservations", roomReservations)

        return "roomres"
    }
}