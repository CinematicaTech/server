package com.cinematica.backend.domain.authorization.routing.state

import com.cinematica.backend.domain.authorization.types.state.AuthorizationStateRequest
import com.cinematica.backend.domain.authorization.types.state.AuthorizationStateResponse
import com.cinematica.backend.domain.authorization.usecases.state.GetAuthorizationStateUseCase
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.call
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.get
import io.ktor.server.routing.route

fun Route.authorizationStateRouting(getAuthorizationStateUseCase: GetAuthorizationStateUseCase) {
    route("auth/state") {
        get {
            val email = call.request.queryParameters["email"]

            if (email.isNullOrBlank()) {
                call.respond(HttpStatusCode.BadRequest, "Email parameter is missing or blank.")
                return@get
            }

            val authorizationStateData = AuthorizationStateRequest(email = email)

            if (authorizationStateData.validationErrorMessage != null) {
                call.respond(HttpStatusCode.BadRequest, authorizationStateData.validationErrorMessage)
                return@get
            }

            val authorizationState = getAuthorizationStateUseCase.execute(authorizationStateData)

            val result = AuthorizationStateResponse(state = authorizationState.value)
            call.respond(HttpStatusCode.OK, result)
        }
    }
}
