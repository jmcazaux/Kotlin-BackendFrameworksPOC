package com.ironbird.application.interfaces.api

import com.ironbird.application.infrastructure.persistence.TestWithDb
import com.ironbird.domain.data.entities.Room
import com.ironbird.plugins.configureRouting
import com.ironbird.plugins.configureSerialization
import io.kotest.assertions.json.shouldBeEmptyJsonArray
import io.kotest.assertions.json.shouldBeJsonArray
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.shouldBe
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.server.testing.*
import kotlinx.serialization.json.Json
import org.junit.jupiter.api.Test

class RoomsAPIRouterTest : TestWithDb() {
    
    @Test
    fun `GET on api-rooms returns all rooms`() = testApplication {

        prefillDb()

        application {
            configureSerialization()
            configureRouting()
        }

        val client = createClient {

        }
        val response = client.get("/api/rooms")

        response.status shouldBe HttpStatusCode.OK
        response.bodyAsText().shouldBeJsonArray()

        val rooms = Json.decodeFromString<List<Room>>(response.bodyAsText())

        rooms shouldHaveSize 4

    }

    @Test
    fun `GET on api-rooms returns empty array when no room`() = testApplication {

        application {
            configureSerialization()
            configureRouting()
        }

        val client = createClient {

        }
        val response = client.get("/api/rooms")

        response.status shouldBe HttpStatusCode.OK
        response.bodyAsText().shouldBeEmptyJsonArray()
    }
}
