package com.ironbird.plugins

import com.ironbird.application.infrastructure.persistence.RoomsRepository
import com.ironbird.application.interfaces.api.RoomsAPIRouter
import com.ironbird.domain.usecases.GetRoomUseCase
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureRouting() {

    val getRoomUseCase = GetRoomUseCase(RoomsRepository())

    install(IgnoreTrailingSlash)

    routing {

        // API routes
        route("/api") {

            // /api/rooms routes
            route("/rooms") {
                RoomsAPIRouter(this, getRoomUseCase)
            }
        }

        get("/") {
            call.respondText("Hello World!")
        }

    }
}
