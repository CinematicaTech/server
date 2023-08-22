package com.cinematica.backend.domain.authorization.new.routing

import com.cinematica.backend.domain.authorization.new.routing.signup.signUpRouting
import com.cinematica.backend.domain.authorization.new.usecases.SignUpUseCase
import io.ktor.server.application.Application
import io.ktor.server.routing.routing

fun Application.configureAuthorizationRouting(
    signUpUseCase: SignUpUseCase,
) {
    routing {
        signUpRouting(signUpUseCase)
    }
}