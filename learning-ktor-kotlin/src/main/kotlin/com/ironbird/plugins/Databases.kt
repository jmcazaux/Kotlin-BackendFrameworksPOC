package com.ironbird.plugins

import com.ironbird.application.infrastructure.persistence.daos.Guests
import com.ironbird.application.infrastructure.persistence.daos.Rooms
import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction

fun configureDatabases() {

    val hikariConfig = HikariConfig().apply {
        jdbcUrl = "jdbc:h2:mem:run;DB_CLOSE_DELAY=-1"
        driverClassName = "org.h2.Driver"
        username = "root"
        password = ""
        maximumPoolSize = 10
    }


    val dataSource = HikariDataSource(hikariConfig)
    val database = Database.connect(dataSource)

    // Initializing DB schema (as we use in-memory DB, no need for upgrading , etc.)
    transaction(database) {
        SchemaUtils.create(Rooms, Guests)
    }

    // Populate the data
    transaction(database) {
        // Reading the resource file and extracting SQL text
        val lines = this::class.java.getResourceAsStream("/data/data.sql")?.bufferedReader()?.readLines()
            ?: throw RuntimeException("Missing database hydration file at /resources/data/data.sql")
        val sqlText = lines.joinToString("\n")

        // Executing the resulting SQL
        exec(sqlText)
    }
}
