package com.cinematica.backend.domain.authorization.routing

import com.cinematica.backend.domain.authorization.routing.signin.signInRouting
import com.cinematica.backend.domain.authorization.routing.signup.signUpRouting
import com.cinematica.backend.domain.authorization.routing.state.authorizationStateRouting
import com.cinematica.backend.domain.authorization.usecases.signin.SignInUseCase
import com.cinematica.backend.domain.authorization.usecases.signup.SignUpUseCase
import com.cinematica.backend.domain.authorization.usecases.state.GetAuthorizationStateUseCase
import io.ktor.server.application.Application
import io.ktor.server.routing.routing
import io.ktor.server.routing.get

fun Application.configureAuthorizationRouting(
    signUpUseCase: SignUpUseCase,
    signInUseCase: SignInUseCase,
    getAuthorizationStateUseCase: GetAuthorizationStateUseCase
) {
    routing {
        // todo remove it
        get("/validation") {
            throw InterruptedException("this is a interr ssds exception")
        }
        signUpRouting(signUpUseCase)
        signInRouting(signInUseCase)
        authorizationStateRouting(getAuthorizationStateUseCase)
    }
}