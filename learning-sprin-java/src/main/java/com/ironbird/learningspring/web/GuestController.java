package com.ironbird.learningspring.web;

import com.ironbird.learningspring.business.ReservationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/guests")
public class GuestController {
    private final ReservationService reservationService;

    public GuestController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }


    @RequestMapping(method = RequestMethod.GET)
    public String getGuests(Model model) {
        System.out.println("GET /guests ".repeat(10));
        model.addAttribute("guests", reservationService.getHotelGuests());
        return "hotel-guests";
    }
}
