package com.ironbird.learningquarkus.data.repository

import com.ironbird.learningquarkus.business.roomreservation.RoomReservation
import com.ironbird.learningquarkus.data.entity.Reservation
import io.quarkus.hibernate.orm.panache.kotlin.PanacheRepository
import java.sql.Date

class ReservationRepository : PanacheRepository<Reservation> {

/*    fun getRoomsOccupancy(day: Date): List<RoomReservation> {
        val q = """
            select 
                r.roomId,
                
        """.trimIndent()
        var r = this.find("").project(RoomReservation)
        return r
    }*/
}
