package com.cinematica.backend.domain.authorization.routes

import com.cinematica.backend.domain.authorization.routes.state.authorizationStateRouting
import io.ktor.server.routing.Route

fun Route.configureAuthorizationRouting() {
    authorizationStateRouting()
}