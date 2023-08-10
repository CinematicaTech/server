package com.cinematica.backend.domain.authorization.routes.state

import com.cinematica.backend.domain.authorization.types.state.AuthorizationStateRequest
import com.cinematica.backend.domain.authorization.usecases.GetAuthorizationStateUseCase
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.call
import io.ktor.server.request.receiveNullable
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.get

fun Route.authorizationStateRouting(
    getAuthorizationStateUseCase: GetAuthorizationStateUseCase
) {
    get("/auth/state") {
        val requestData = call.receiveNullable<AuthorizationStateRequest>() ?: kotlin.run {
            call.respond(HttpStatusCode.BadRequest)
            return@get
        }

        val result = getAuthorizationStateUseCase.execute(requestData)

        call.respond(result)
    }
}