package com.cinematica.backend.domain.authorization.routes

import com.cinematica.backend.domain.authorization.routes.state.authorizationStateRouting
import com.cinematica.backend.domain.authorization.usecases.GetAuthorizationStateUseCase
import io.ktor.server.application.Application
import io.ktor.server.routing.routing

fun Application.configureAuthorizationRouting(
    getAuthorizationStateUseCase: GetAuthorizationStateUseCase
) {
    routing {
        authorizationStateRouting(
            getAuthorizationStateUseCase = getAuthorizationStateUseCase
        )
    }
}