package com.ironbird.plugins

import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.serialization.json.Json

fun Application.configureSerialization() {
    install(ContentNegotiation) {
        json(Json { prettyPrint = true })
    }
    routing {
        get("/json/hello") {
                call.respond(mapOf("hello" to "world", "foo" to "bar"))
            }
    }
}
