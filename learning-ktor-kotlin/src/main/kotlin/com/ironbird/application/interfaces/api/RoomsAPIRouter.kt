package com.ironbird.application.interfaces.api

import com.ironbird.domain.usecases.GetRoomUseCase
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

class RoomsAPIRouter(route: Route, getRoomsUseCase: GetRoomUseCase) {

    init {
        route {
            get("/") {
                this.call.respond(getRoomsUseCase.getAll())
            }
        }
    }
}
