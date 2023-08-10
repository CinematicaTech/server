package com.cinematica.backend.domain.authorization.routing.signup

import com.cinematica.backend.domain.authorization.types.signup.SignUpRequest
import com.cinematica.backend.domain.authorization.usecases.signup.SignUpUseCase
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.call
import io.ktor.server.request.receiveNullable
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.post

fun Route.signUpRouting(
    signUpUseCase: SignUpUseCase
) {
    post("/auth/signup") {
        val signUpData = kotlin.runCatching {
            call.receiveNullable<SignUpRequest>()
        }.getOrNull() ?: kotlin.run {
            call.respond(HttpStatusCode.BadRequest)
            return@post
        }

        if (signUpData.validationErrorMessage != null) {
            call.respond(signUpData.validationErrorMessage)
            return@post
        }

        val result = signUpUseCase.execute(signUpData)

        call.respond(HttpStatusCode.OK)
    }
}