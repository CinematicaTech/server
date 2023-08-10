package com.cinematica.backend.domain.authorization.routes.signup

import com.cinematica.backend.domain.authorization.types.signup.SignUpRequest
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.call
import io.ktor.server.request.receiveNullable
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.get

fun Route.signUpRouting(
) {
    get("/auth/signup") {
        val requestData = call.receiveNullable<SignUpRequest>() ?: kotlin.run {
            call.respond(HttpStatusCode.BadRequest)
            return@get
        }

        // val result = getAuthorizationStateUseCase.execute(requestData)

        // call.respond(result)
    }
}