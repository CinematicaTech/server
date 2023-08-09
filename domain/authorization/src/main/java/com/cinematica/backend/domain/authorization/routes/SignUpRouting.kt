package com.cinematica.backend.domain.authorization.routes

import io.ktor.server.routing.Route
import io.ktor.server.routing.post

fun Route.signUpRouting() {
    post("auth/signup") {

    }
}