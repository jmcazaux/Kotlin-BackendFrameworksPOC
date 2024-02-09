package com.ironbird.learningquarkus.web


import com.ironbird.learningquarkus.data.repository.GuestRepository
import io.quarkus.logging.Log
import io.quarkus.qute.CheckedTemplate
import io.quarkus.qute.TemplateInstance
import io.smallrye.common.annotation.Blocking
import jakarta.ws.rs.GET
import jakarta.ws.rs.Path
import jakarta.ws.rs.Produces
import jakarta.ws.rs.core.MediaType
import org.eclipse.microprofile.openapi.annotations.Operation


@Path("/")
class WebController(private val guestRepository: GuestRepository) {

    @CheckedTemplate(basePath = "web")
    object Templates {
        @JvmStatic
        external fun guests(): TemplateInstance?
    }


    @GET
    @Path("guests")
    @Produces(MediaType.TEXT_HTML)
    @Blocking
    @Operation(hidden = true)
    fun guestsPage(): TemplateInstance? {
        Log.info("Getting guests page...")

        val guests = guestRepository.allGuests()
        Log.info("Got ${guests.size} guests")

        val template = Templates.guests()?.data("guests", guests)
        if (template == null) {
            Log.error("Template did not load")
            return null
        }

        Log.info("Returning template $template")
        return template
    }
}
