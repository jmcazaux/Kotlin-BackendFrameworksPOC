package com.ironbird.learningspring.util;

import com.ironbird.learningspring.data.*;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.sql.Date;

@Component
public class AppStartupEvent implements ApplicationListener<ApplicationReadyEvent> {

    private final RoomRepository roomRepository;
    private final GuestRepository guestRepository;
    private final ReservationRepository reservationRepository;

    public AppStartupEvent(RoomRepository roomRepository, GuestRepository guestRepository, ReservationRepository reservationRepository) {
        this.roomRepository = roomRepository;
        this.guestRepository = guestRepository;
        this.reservationRepository = reservationRepository;
    }

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        Iterable<Room> rooms = this.roomRepository.findAll();
        rooms.forEach(System.out::println);
        System.out.println("*".repeat(80));

        Iterable<Guest> guests = guestRepository.findAll();
        guests.forEach(System.out::println);
        System.out.println("*".repeat(80));

        Iterable<Reservation> reservations = reservationRepository.findAll();
        reservations.forEach(System.out::println);
        System.out.println("*".repeat(80));

        Iterable<Reservation> theReservations = reservationRepository.findReservationByReservationDate(new Date(2022, 01, 01));
        theReservations.forEach(System.out::println);
        System.out.println("*".repeat(80));
    }
}
