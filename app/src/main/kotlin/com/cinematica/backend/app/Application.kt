package com.cinematica.backend.app

import com.cinematica.backend.app.constants.ArgumentsConstants
import com.cinematica.backend.app.constants.EnvironmentConstants
import com.cinematica.backend.app.constants.FailureMessages
import com.cinematica.backend.app.dependencies.AppModule
import com.cinematica.backend.app.services.monitoring.configureMonitoring
import com.cinematica.backend.app.services.serialization.configureSerialization
import com.cinematica.backend.domain.authorization.routing.configureAuthorizationRouting
import com.cinematica.backend.domain.authorization.types.common.AuthorizationRequest
import com.cinematica.backend.domain.authorization.usecases.signin.SignInUseCase
import com.cinematica.backend.domain.authorization.usecases.signup.SignUpUseCase
import com.cinematica.backend.domain.authorization.usecases.state.AuthorizationStateUseCase
import com.cinematica.backend.foundation.cli.asArguments
import com.cinematica.backend.foundation.cli.getNamedIntOrNull
import com.cinematica.backend.foundation.security.token.data.TokenConfig
import com.typesafe.config.ConfigFactory
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.call
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import io.ktor.server.request.receiveNullable
import io.ktor.server.response.respond
import io.ktor.server.routing.get
import io.ktor.server.routing.post
import io.ktor.server.routing.routing
import org.koin.core.context.startKoin
import org.koin.dsl.module
import org.litote.kmongo.coroutine.CoroutineDatabase
import org.litote.kmongo.coroutine.coroutine
import org.litote.kmongo.reactivestreams.KMongo

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

    val coroutineClient = KMongo.createClient(databaseUrl).coroutine
    val database = coroutineClient.getDatabase("cinematica_database")

    val dynamicModule = module {
        single<CoroutineDatabase> { database }
        single<TokenConfig> {
            TokenConfig(
                issuer = issuer,
                audience = audience,
                expiresIn = 365L * 1000L * 60L * 60L * 24L,
                secret = secret
            )
        }
    }

    val koin = startKoin {
        modules(AppModule + dynamicModule)
    }.koin

    embeddedServer(Netty, port) {
        configureMonitoring()
        configureSerialization()
//        configureAuthorizationRouting(
//            signUpUseCase = koin.get<SignUpUseCase>(),
//            signInUseCase = koin.get<SignInUseCase>(),
//            authorizationStateUseCase = koin.get<AuthorizationStateUseCase>()
//        )
        routing {
            val signUpUseCase = koin.get<SignUpUseCase>()
            get("/hello") {
                println("hello world")
                call.respond("HELLO FUC*ING")
            }
            post("/auth/signup") {
                val authorizationRequest = kotlin.runCatching {
                    call.receiveNullable<AuthorizationRequest>()
                }.getOrNull() ?: kotlin.run {
                    call.respond(HttpStatusCode.BadRequest)
                    return@post
                }

                if (authorizationRequest.validationErrorMessage != null) {
                    call.respond(authorizationRequest.validationErrorMessage.toString())
                    return@post
                }

                val result = signUpUseCase.execute(authorizationRequest)

                result.getOrNull()?.let { request ->
                    call.respond(HttpStatusCode.OK, request)
                } ?: call.respond(HttpStatusCode.BadRequest, result.exceptionOrNull()?.message.toString())
            }
        }
        println("Server started on address: http://127.0.0.1:$port")
    }.start(true)
}