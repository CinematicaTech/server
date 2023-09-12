package com.cinematica.backend.app.services.exceptions

import com.cinematica.exception.handling.ExceptionResponse
import com.cinematica.exception.handling.authorization.AuthorizationException
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.Application
import io.ktor.server.application.install
import io.ktor.server.plugins.statuspages.StatusPages
import io.ktor.server.response.respond

fun Application.configureExceptions() {
    install(StatusPages) {
        exception<Throwable> { call, throwable ->
            when (throwable) {
//                is InterruptedException -> {
//                    call.respond(
//                        status = HttpStatusCode.BadRequest,
//                        message = ExceptionResponse(
//                            message = "${throwable.message}",
//                            code = HttpStatusCode.BadRequest.value
//                        )
//                    )
//                }

                is AuthorizationException.SignUpException -> {

                }

                is AuthorizationException.SignInException -> {

                }

                is AuthorizationException.GetAuthorizationStateException -> {

                }
            }
        }
    }
}