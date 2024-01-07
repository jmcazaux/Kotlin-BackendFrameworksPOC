package com.ironbird.learningspring.webservice;

import com.ironbird.learningspring.business.ReservationService;
import com.ironbird.learningspring.business.RoomReservation;
import com.ironbird.learningspring.data.Guest;
import com.ironbird.learningspring.data.GuestRepository;
import com.ironbird.learningspring.data.Room;
import com.ironbird.learningspring.data.RoomRepository;
import com.ironbird.learningspring.util.DateUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api")
public class WebserviceController {
    private final DateUtils dateUtils;
    private final ReservationService reservationService;


    public WebserviceController(DateUtils dateUtils, ReservationService reservationService, GuestRepository guestRepository, RoomRepository roomRepository) {
        this.dateUtils = dateUtils;
        this.reservationService = reservationService;
    }

    @GetMapping(path = "/reservations")
    public List<RoomReservation> getReservations(@RequestParam(value = "date", required = false) String dateParam) {
        Date date = dateUtils.createDateFromDateString(dateParam);
        return this.reservationService.getRoomReservationsForDate(date);

    }

    @GetMapping(path = "/guests")
    public List<Guest> getGuests() {
        return this.reservationService.getHotelGuests();
    }

    @PostMapping(path = "/guests")
    @ResponseStatus(HttpStatus.CREATED)
    public void addGuest(@RequestBody Guest guest) {
        this.reservationService.addGuest(guest);
    }

    @GetMapping(path = "/rooms")
    public List<Room> getRooms() {
        return reservationService.getRooms();
    }
}
