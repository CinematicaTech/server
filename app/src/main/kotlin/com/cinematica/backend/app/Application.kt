package com.cinematica.backend.app

import com.cinematica.backend.app.constants.ArgumentsConstants
import com.cinematica.backend.app.constants.EnvironmentConstants
import com.cinematica.backend.app.dependencies.AuthorizationModule
import com.cinematica.backend.app.services.monitoring.configureMonitoring
import com.cinematica.backend.app.services.serialization.configureSerialization
import com.cinematica.backend.domain.authorization.routing.configureAuthorizationRouting
import com.cinematica.backend.domain.authorization.usecases.signup.SignUpUseCase
import com.cinematica.backend.foundation.cli.asArguments
import com.cinematica.backend.foundation.cli.getNamedIntOrNull
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import org.koin.core.context.startKoin


fun main(args: Array<String>) {
    val arguments = args.asArguments()

    val port = arguments.getNamedIntOrNull(ArgumentsConstants.PORT)
        ?: System.getenv(EnvironmentConstants.APPLICATION_PORT)?.toIntOrNull()
        ?: 8080

    val koin = startKoin {
        modules(AuthorizationModule)
    }.koin

    embeddedServer(Netty, port) {
        configureMonitoring()
        configureSerialization()
        configureAuthorizationRouting(
            signUpUseCase = koin.get<SignUpUseCase>()
        )
    }.start(true)
}