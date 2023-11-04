package com.ironbird.learningspring.web;

import com.ironbird.learningspring.business.ReservationService;
import com.ironbird.learningspring.business.RoomReservation;
import com.ironbird.learningspring.util.DateUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;


@Controller
@RequestMapping("/reservations")
public class RoomReservationController {

    private final DateUtils dateUtils;
    private final ReservationService reservationService;

    public RoomReservationController(DateUtils dateUtils, ReservationService reservationService) {
        this.dateUtils = dateUtils;
        this.reservationService = reservationService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String getReservations(@RequestParam(value = "date", required = false) String reservationDateParam, Model model) {
        Date reservationDate = dateUtils.createDateFromDateString(reservationDateParam);
        List<RoomReservation> roomReservations = reservationService.getRoomReservationsForDate(reservationDate);
        model.addAttribute("roomReservations", roomReservations);
        return "roomres";
    }
}
