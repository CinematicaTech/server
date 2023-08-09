package com.cinematica.backend.app

import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun main() {
    embeddedServer(Netty, 8080) {
        routing {
            get("/helloworld") {
                call.respond("Hello Fucking World")
            }
        }
    }.start(true)
}