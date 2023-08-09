package com.cinematica.backend.app

import com.cinematica.backend.app.constants.ArgumentsConstants
import com.cinematica.backend.app.constants.EnvironmentConstants
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun main(args: Array<String>) {
    val arguments = args.asArguments()

    val port = arguments.getNamedIntOrNull(ArgumentsConstants.PORT)
        ?: System.getenv(EnvironmentConstants.APPLICATION_PORT)?.toIntOrNull()
        ?: 8080

    embeddedServer(Netty, port) {
        routing {
            get("/hello") {
                call.respond("Hello Fucking World")
            }
        }
    }.start(true)
}