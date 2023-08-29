package com.cinematica.backend.domain.authorization.routing.state

import com.cinematica.backend.domain.authorization.types.state.AuthorizationStateRequest
import com.cinematica.backend.domain.authorization.types.state.AuthorizationStateResponse
import com.cinematica.backend.domain.authorization.usecases.state.GetAuthorizationStateUseCase
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.call
import io.ktor.server.request.receiveNullable
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.get

fun Route.authorizationStateRouting(
    getAuthorizationStateUseCase: GetAuthorizationStateUseCase
) {
    get("auth/state") {
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

        val authorizationState = getAuthorizationStateUseCase.execute(authorizationStateData)
        val result = AuthorizationStateResponse(
            state = authorizationState.value
        )

        call.respond(HttpStatusCode.OK, result)
    }
}