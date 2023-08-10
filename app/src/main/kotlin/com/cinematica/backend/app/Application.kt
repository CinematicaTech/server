package com.cinematica.backend.app

import com.cinematica.backend.app.constants.ArgumentsConstants
import com.cinematica.backend.app.constants.EnvironmentConstants
import com.cinematica.backend.app.services.monitoring.configureMonitoring
import com.cinematica.backend.app.services.serialization.configureSerialization
import com.cinematica.backend.foundation.cli.asArguments
import com.cinematica.backend.foundation.cli.getNamedIntOrNull
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty


fun main(args: Array<String>) {
    val arguments = args.asArguments()

    val port = arguments.getNamedIntOrNull(ArgumentsConstants.PORT)
        ?: System.getenv(EnvironmentConstants.APPLICATION_PORT)?.toIntOrNull()
        ?: 8080

//    val koin = startKoin {
//        modules(AuthorizationModule)
//    }.koin

    embeddedServer(Netty, port) {
        configureMonitoring()
        configureSerialization()
//        configureAuthorizationRouting(
//            koin.get<GetAuthorizationStateUseCase>()
//        )
    }.start(true)
}