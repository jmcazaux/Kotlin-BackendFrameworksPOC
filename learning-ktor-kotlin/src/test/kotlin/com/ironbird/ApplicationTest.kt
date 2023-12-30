package com.ironbird

import com.ironbird.plugins.configureRouting
import io.kotest.matchers.shouldBe
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.server.testing.*
import org.junit.jupiter.api.Test


class ApplicationTest {
    @Test
    fun testRoot() = testApplication {
        application {
            configureRouting()
        }
        client.get("/").apply {
            status shouldBe HttpStatusCode.OK
            bodyAsText() shouldBe "Hello World!"
        }
    }
}
