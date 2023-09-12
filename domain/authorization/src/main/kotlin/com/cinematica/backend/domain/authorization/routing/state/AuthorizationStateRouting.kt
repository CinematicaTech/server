package com.cinematica.backend.domain.authorization.routing.state

import com.cinematica.backend.domain.authorization.types.state.AuthorizationStateRequest
import com.cinematica.backend.domain.authorization.types.state.AuthorizationStateResponse
import com.cinematica.backend.domain.authorization.usecases.state.GetAuthorizationStateUseCase
import com.cinematica.exception.handling.authorization.AuthorizationException
import com.cinematica.exception.handling.authorization.ExceptionType
import com.cinematica.exception.handling.authorization.messages.AuthorizationExceptionMessage
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
                throw AuthorizationException.createException(
                    message = AuthorizationExceptionMessage.EMAIL_IS_EMPTY,
                    type = ExceptionType.GET_STATE
                )
            }

            val authorizationStateData = AuthorizationStateRequest(email = email)

            if (authorizationStateData.validationErrorMessage != null) {
                throw AuthorizationException.createException(
                    message = authorizationStateData.validationErrorMessage,
                    type = ExceptionType.GET_STATE
                )
            }

            val authorizationState = getAuthorizationStateUseCase.execute(authorizationStateData)

            val result = AuthorizationStateResponse(state = authorizationState.value)
            call.respond(HttpStatusCode.OK, result)
        }
    }
}
