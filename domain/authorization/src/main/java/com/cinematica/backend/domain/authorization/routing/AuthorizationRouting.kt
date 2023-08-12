package com.cinematica.backend.domain.authorization.routing

import com.cinematica.backend.domain.authorization.routing.signup.signUpRouting
import com.cinematica.backend.domain.authorization.routing.state.authorizationStateRouting
import com.cinematica.backend.domain.authorization.usecases.signup.SignUpUseCase
import com.cinematica.backend.domain.authorization.usecases.state.AuthorizationStateUseCase
import io.ktor.server.application.Application
import io.ktor.server.routing.routing

fun Application.configureAuthorizationRouting(
    signUpUseCase: SignUpUseCase,
    authorizationStateUseCase: AuthorizationStateUseCase
) {
    routing {
        signUpRouting(signUpUseCase)
        authorizationStateRouting(authorizationStateUseCase)
    }
}