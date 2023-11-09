package com.ironbird.learningspring.web

import com.ironbird.learningspring.business.ReservationService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod

@Controller
@RequestMapping("/guests")
class GuestController(val reservationService: ReservationService) {

    @RequestMapping(method = [RequestMethod.GET])
    fun getGuests(model: Model): String {
        model.addAttribute("guests", reservationService.getHotelGuests())
        return "hotel-guests"
    }
}