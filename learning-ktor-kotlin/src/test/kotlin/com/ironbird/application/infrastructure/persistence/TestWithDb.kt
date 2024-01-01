package com.ironbird.application.infrastructure.persistence

import com.ironbird.application.infrastructure.persistence.daos.Rooms
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction
import org.junit.jupiter.api.BeforeEach

abstract class TestWithDb {

    private val db: Database = Database.connect("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1", driver = "org.h2.Driver")

    companion object {
        val TABLES = arrayOf(Rooms)
    }

    init {
        transaction(db) {
            SchemaUtils.create(*TABLES)
        }
    }

    fun prefillDb() {
        transaction(db) {
            // Reading the resource file and extracting SQL text
            val lines = this::class.java.getResourceAsStream("/data/test_data.sql")?.bufferedReader()?.readLines()
                ?: throw RuntimeException("Missing database hydration file at /resources/data/test_data.sql")
            val sql = lines.joinToString("\n")

            // Executing the resulting SQL
            exec(sql)
        }
    }

    fun resetDb() {
        transaction(db) {
            var sql = TABLES.map { table -> "TRUNCATE TABLE ${table.tableName};\n" }
                .reduce { truncateSql, sql -> truncateSql + sql }
            exec(sql)
        }
    }

    @BeforeEach
    fun reResetDb() {
        this.resetDb()
    }

}
