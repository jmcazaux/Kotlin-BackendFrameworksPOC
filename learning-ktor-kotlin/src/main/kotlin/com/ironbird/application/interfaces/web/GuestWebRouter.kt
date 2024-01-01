package com.ironbird.application.interfaces.web

import com.ironbird.domain.usecases.GetGuestUseCase
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.thymeleaf.*
import org.h2.engine.User

class GuestWebRouter(route: Route, getGuestUseCase: GetGuestUseCase) {
    init {
        route {
            get("/") {
                val guests = getGuestUseCase.getAllGuests()

                call.respond(ThymeleafContent("hotel-guests", mapOf("guests" to guests)))
            }
        }
    }
}