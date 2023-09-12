@file:Suppress("ExtractKtorModule")

package com.cinematica.backend.app

import com.cinematica.backend.app.constants.ArgumentsConstants
import com.cinematica.backend.app.constants.EnvironmentConstants
import com.cinematica.backend.app.constants.FailureMessages
import com.cinematica.backend.app.dependencies.AppModule
import com.cinematica.backend.app.dependencies.configuration.DatabaseConfig
import com.cinematica.backend.app.services.exceptions.configureExceptions
import com.cinematica.backend.app.services.monitoring.configureMonitoring
import com.cinematica.backend.app.services.serialization.configureSerialization
import com.cinematica.backend.domain.authorization.routing.configureAuthorizationRouting
import com.cinematica.backend.domain.authorization.usecases.signup.SignUpUseCase
import com.cinematica.backend.foundation.cli.asArguments
import com.cinematica.backend.foundation.cli.getNamedIntOrNull
import com.cinematica.backend.foundation.security.token.data.TokenConfig
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import org.koin.core.context.startKoin
import org.koin.dsl.module
import org.ktorm.database.Database
import org.ktorm.entity.Entity
import org.ktorm.schema.Table
import org.ktorm.schema.int
import org.ktorm.schema.varchar

fun main(args: Array<String>) {
    val arguments = args.asArguments()

    val port = arguments.getNamedIntOrNull(ArgumentsConstants.PORT)
        ?: System.getenv(EnvironmentConstants.APPLICATION_PORT)?.toIntOrNull()
        ?: 8080

    val databaseUrl = arguments.getNamedOrNull(ArgumentsConstants.DATABASE_URL)
        ?: System.getenv(EnvironmentConstants.APPLICATION_DATABASE_URL)
        ?: error(FailureMessages.MISSING_DATABASE_URL)

    val secret = arguments.getNamedOrNull(ArgumentsConstants.SECRET)
        ?: System.getenv(EnvironmentConstants.APPLICATION_SECRET)
        ?: error(FailureMessages.MISSING_SECRET)

    val issuer = arguments.getNamedOrNull(ArgumentsConstants.ISSUER)
        ?: System.getenv(EnvironmentConstants.APPLICATION_ISSUER)
        ?: error(FailureMessages.MISSING_ISSUER)

    val audience = arguments.getNamedOrNull(ArgumentsConstants.AUDIENCE)
        ?: System.getenv(EnvironmentConstants.APPLICATION_AUDIENCE)
        ?: error(FailureMessages.MISSING_AUDIENCE)

    val user = arguments.getNamedOrNull(ArgumentsConstants.USER)
        ?: System.getenv(EnvironmentConstants.APPLICATION_USER)
        ?: error(FailureMessages.MISSING_USER)

    val password = arguments.getNamedOrNull(ArgumentsConstants.PASSWORD)
        ?: System.getenv(EnvironmentConstants.APPLICATION_PASSWORD)
        ?: error(FailureMessages.MISSING_PASSWORD)

    val dynamicModule = module {
        single<TokenConfig> {
            TokenConfig(
                issuer = issuer,
                audience = audience,
                expiresIn = 365L * 1000L * 60L * 60L * 24L,
                secret = secret
            )
        }
        single {
            DatabaseConfig(url = databaseUrl, user = user, password = password)
        }
    }

    val koin = startKoin {
        modules(AppModule + dynamicModule)
    }.koin

    embeddedServer(Netty, port) {
        configureMonitoring()
        configureSerialization()
        configureExceptions()

        configureAuthorizationRouting(
            signUpUseCase = koin.get(),
            signInUseCase = koin.get(),
            getAuthorizationStateUseCase = koin.get(),
        )
    }.start(true)
}