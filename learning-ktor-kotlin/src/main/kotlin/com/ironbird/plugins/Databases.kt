package com.ironbird.plugins

import com.ironbird.domain.data.entities.User
import com.ironbird.application.repositories.RoomsRepository
import com.ironbird.application.repositories.UsersRepository
import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction

fun Application.configureDatabases() {

    val hikariConfig = HikariConfig().apply {
        jdbcUrl = "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1"
        driverClassName = "org.h2.Driver"
        username = "root"
        password = ""
        maximumPoolSize = 10
    }


    val dataSource = HikariDataSource(hikariConfig)
    val database = Database.connect(dataSource)

    // Initializing DB schema (as we use in-memory DB, no need for upgrading , etc.)
    transaction(database) {
        SchemaUtils.create(UsersRepository.Users, RoomsRepository.Rooms)
    }

    // Populate the data
    transaction(database) {
        // Reading the resource file and extracting SQL text
        val lines = this::class.java.getResourceAsStream("/data/data.sql")?.bufferedReader()?.readLines() ?: throw RuntimeException("Missing database hydration file at /resources/data/data.sql")
        val sqlText = lines.joinToString("\n")

        // Executing the resulting SQL
        exec(sqlText)
    }

    val usersRepository = UsersRepository()

    routing {
        // Create user
        post("/users") {
            val user = call.receive<User>()
            val id = usersRepository.create(user)
            call.respond(HttpStatusCode.Created, id)
        }

        // Read user
        get("/users/{id}") {
            val id = call.parameters["id"]?.toInt() ?: throw IllegalArgumentException("Invalid ID")
            val user = usersRepository.read(id)
            if (user != null) {
                call.respond(HttpStatusCode.OK, user)
            } else {
                call.respond(HttpStatusCode.NotFound)
            }
        }

        get("/users") {
            val users = usersRepository.readAll()
            if (users.isNotEmpty()) {
                call.respond(HttpStatusCode.OK, users)
            } else {
                call.respond(HttpStatusCode.NotFound)
            }
        }


        // Update user
        put("/users/{id}") {
            val id = call.parameters["id"]?.toInt() ?: throw IllegalArgumentException("Invalid ID")
            val user = call.receive<User>()
            usersRepository.update(id, user)
            call.respond(HttpStatusCode.OK)
        }

        // Delete user
        delete("/users/{id}") {
            val id = call.parameters["id"]?.toInt() ?: throw IllegalArgumentException("Invalid ID")
            usersRepository.delete(id)
            call.respond(HttpStatusCode.OK)
        }
    }
}
