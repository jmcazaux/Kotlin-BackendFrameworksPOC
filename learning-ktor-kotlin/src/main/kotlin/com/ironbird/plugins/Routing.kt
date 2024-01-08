package com.ironbird.plugins

import com.ironbird.application.infrastructure.persistence.GuestsRepository
import com.ironbird.application.infrastructure.persistence.RoomsRepository
import com.ironbird.application.interfaces.api.RoomsAPIRouter
import com.ironbird.application.interfaces.web.GuestWebRouter
import com.ironbird.application.infrastructure.persistence.ReservationRepository
import com.ironbird.application.interfaces.web.ReservationWebRouter
import com.ironbird.domain.usecases.GetGuestUseCase
import com.ironbird.domain.usecases.GetReservationsUseCase
import com.ironbird.domain.usecases.GetRoomUseCase
import io.ktor.server.application.*
import io.ktor.server.http.content.*
import io.ktor.server.routing.*

fun Application.configureRouting() {

    val getRoomUseCase = GetRoomUseCase(RoomsRepository())
    val getGuestUseCase = GetGuestUseCase(GuestsRepository())
    val getReservationsUseCase = GetReservationsUseCase(ReservationRepository())

    install(IgnoreTrailingSlash)

    routing {

        // API routes
        route("/api") {

            // /api/rooms routes
            route("/rooms") {
                RoomsAPIRouter(this, getRoomUseCase)
            }
        }

        // Web routes
        route("/web") {
            route("/guests") {
                GuestWebRouter(this, getGuestUseCase)
            }
            route("/reservations") {
                ReservationWebRouter(this, getReservationsUseCase)
            }
        }

        // Serving /[index.html] from resources/static/[index.html]
        staticResources("/", "static")
    }

}
