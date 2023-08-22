package com.cinematica.backend.domain.authorization.old.routing.signin

import com.cinematica.backend.domain.authorization.old.types.common.AuthorizationRequest
import com.cinematica.backend.domain.authorization.old.usecases.signin.SignInUseCase
import com.cinematica.backend.domain.authorization.old.usecases.signup.SignUpUseCase
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.call
import io.ktor.server.request.receiveNullable
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.post

fun Route.signInRouting(
    signInUseCase: SignInUseCase
) {
    post("auth/signin") {
        val authorizationRequest = kotlin.runCatching {
            call.receiveNullable<AuthorizationRequest>()
        }.getOrNull() ?: kotlin.run {
            call.respond(HttpStatusCode.BadRequest)
            return@post
        }

        if (authorizationRequest.validationErrorMessage != null) {
            call.respond(authorizationRequest.validationErrorMessage)
            return@post
        }

        val result = signInUseCase.execute(authorizationRequest)

        result.getOrNull()?.let { request ->
            call.respond(HttpStatusCode.OK, request)
        } ?: call.respond(HttpStatusCode.BadRequest, result.exceptionOrNull()?.message.toString())
    }
}