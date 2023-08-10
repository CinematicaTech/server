package com.cinematica.backend.domain.authorization.routes

import com.cinematica.backend.domain.authorization.routes.state.authorizationStateRouting
import com.cinematica.backend.domain.authorization.usecases.GetAuthorizationStateUseCase
import io.ktor.server.routing.Route

fun Route.configureAuthorizationRouting(
    getAuthorizationStateUseCase: GetAuthorizationStateUseCase
) {
    authorizationStateRouting(
        getAuthorizationStateUseCase = getAuthorizationStateUseCase
    )
}