package com.cinematica.backend.domain.authorization.old.routing

import com.cinematica.backend.domain.authorization.old.routing.signin.signInRouting
import com.cinematica.backend.domain.authorization.old.routing.signup.signUpRouting
import com.cinematica.backend.domain.authorization.old.routing.state.authorizationStateRouting
import com.cinematica.backend.domain.authorization.old.usecases.signin.SignInUseCase
import com.cinematica.backend.domain.authorization.old.usecases.signup.SignUpUseCase
import com.cinematica.backend.domain.authorization.old.usecases.state.AuthorizationStateUseCase
import io.ktor.server.application.Application
import io.ktor.server.routing.routing

fun Application.configureAuthorizationRouting(
    signUpUseCase: SignUpUseCase,
    signInUseCase: SignInUseCase,
    authorizationStateUseCase: AuthorizationStateUseCase
) {
    routing {
        signUpRouting(signUpUseCase)
        signInRouting(signInUseCase)
        authorizationStateRouting(authorizationStateUseCase)
    }
}