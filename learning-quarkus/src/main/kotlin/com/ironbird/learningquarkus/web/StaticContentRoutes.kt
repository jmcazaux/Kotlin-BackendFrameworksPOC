package com.ironbird.learningquarkus.web

import io.quarkus.logging.Log
import io.quarkus.runtime.StartupEvent
import io.vertx.ext.web.Router
import io.vertx.ext.web.handler.StaticHandler
import jakarta.enterprise.context.ApplicationScoped
import jakarta.enterprise.event.Observes

@ApplicationScoped
class StaticContentRoutes {
    fun installStaticRoute(@Observes startupEvent: StartupEvent?, router: Router) {
        Log.info("Installing route: /static")
        router.route()
            .path("/static/*")
            .handler(StaticHandler.create("web/static/"))

        Log.info("Installing route: /")
        router.route()
            .path("/*")
            .handler(StaticHandler.create("web/"))
    }
}