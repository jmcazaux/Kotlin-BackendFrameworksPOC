package com.ironbird.learningquarkus.utils

import com.ironbird.learningquarkus.data.repository.GuestRepository
import io.quarkus.runtime.ShutdownEvent
import io.quarkus.runtime.StartupEvent
import jakarta.enterprise.context.ApplicationScoped
import jakarta.enterprise.event.Observes
import io.quarkus.logging.Log


@ApplicationScoped
class AppEventsListener(
    private val guestRepository: GuestRepository
) {

    fun onStart(@Observes event: StartupEvent?) {
        Log.info("The application is starting...")
    }

    fun onStop(@Observes event: ShutdownEvent?) {
        Log.info("The application is stopping...")
    }

}
