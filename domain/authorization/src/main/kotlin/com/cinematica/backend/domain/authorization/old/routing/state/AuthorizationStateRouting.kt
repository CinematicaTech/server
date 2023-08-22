package com.cinematica.backend.domain.authorization.old.routing.state

import com.cinematica.backend.domain.authorization.old.types.state.AuthorizationStateRequest
import com.cinematica.backend.domain.authorization.old.usecases.state.AuthorizationStateUseCase
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.call
import io.ktor.server.request.receiveNullable
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.get

fun Route.authorizationStateRouting(
    authorizationStateUseCase: AuthorizationStateUseCase
) {
    get("/auth/state") {
        val authorizationStateData = kotlin.runCatching {
            call.receiveNullable<AuthorizationStateRequest>()
        }.getOrNull() ?: kotlin.run {
            call.respond(HttpStatusCode.BadRequest)
            return@get
        }

        if (authorizationStateData.validationErrorMessage != null) {
            call.respond(authorizationStateData.validationErrorMessage)
            return@get
        }
        val result = authorizationStateUseCase.execute(authorizationStateData)

        call.respond(HttpStatusCode.OK, result)
    }
}