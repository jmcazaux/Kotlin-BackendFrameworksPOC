package com.ironbird.application.interfaces.web

import com.ironbird.domain.usecases.GetReservationsUseCase
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.thymeleaf.*
import java.text.ParseException
import java.time.LocalDate

class ReservationWebRouter(route: Route, getReservationsUseCase: GetReservationsUseCase) {
    init {
        route {
            get("/") {
                val queryDate = dateFromDateString(call.request.queryParameters["date"])

                val reservations = getReservationsUseCase.getByDate(queryDate)

                call.respond(ThymeleafContent("room-reservations.html", mapOf("roomReservations" to reservations)))
            }
        }
    }

}

fun dateFromDateString(dateString: String?): LocalDate {

    return if (dateString?.isNotEmpty() == true) {
        try {
            LocalDate.parse(dateString)
        } catch (e: ParseException) {
            LocalDate.now()
        }
    } else {
        LocalDate.now()
    }
}