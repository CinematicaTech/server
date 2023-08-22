@file:Suppress("ExtractKtorModule")

package com.cinematica.backend.app

import com.cinematica.backend.app.constants.ArgumentsConstants
import com.cinematica.backend.app.constants.EnvironmentConstants
import com.cinematica.backend.app.constants.FailureMessages
import com.cinematica.backend.app.dependencies.AppModule
import com.cinematica.backend.app.dependencies.configuration.DatabaseConfig
import com.cinematica.backend.app.services.monitoring.configureMonitoring
import com.cinematica.backend.app.services.serialization.configureSerialization
import com.cinematica.backend.domain.authorization.old.types.Test
import com.cinematica.backend.foundation.cli.asArguments
import com.cinematica.backend.foundation.cli.getNamedIntOrNull
import com.cinematica.backend.foundation.exposed.suspendedTransaction
import com.cinematica.backend.foundation.security.token.data.TokenConfig
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.call
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.get
import io.ktor.server.routing.routing
import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.transactions.transaction
import org.koin.core.context.startKoin
import org.koin.dsl.module

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
            DatabaseConfig(databaseUrl)
        }
    }

    val koin = startKoin {
        modules(AppModule + dynamicModule)
    }.koin

    val database = koin.get<Database>()

    transaction(database) {
        SchemaUtils.create(Users)
    }

    embeddedServer(Netty, port) {
        configureMonitoring()
        configureSerialization()
//        configureAuthorizationRouting(
//            signUpUseCase = koin.get<SignUpUseCase>(),
//            signInUseCase = koin.get<SignInUseCase>(),
//            authorizationStateUseCase = koin.get<AuthorizationStateUseCase>()
//        )
        routing {
            get("/hello") {
                val test = call.receive<Test>()

                println("test: $test")
                println("hello world")

                try {
                    suspendedTransaction(database) {
                        Users.insert {
                            it[username] = test.test
                        }
                    }
                    call.respond("success")
                } catch (e: Exception) {
                    println("e: ${e.message}")
                    call.respond(HttpStatusCode.BadGateway,"failure, e: ${e.message}")
                }
            }
        }
        println("Server started on address: http://127.0.0.1:$port")
    }.start(true)
}


object Users : Table() {
    val username: Column<String> = varchar("name", length = 50)
}