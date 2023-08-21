@file:Suppress("ExtractKtorModule")

package com.cinematica.backend.app

import com.cinematica.backend.app.constants.ArgumentsConstants
import com.cinematica.backend.app.constants.EnvironmentConstants
import com.cinematica.backend.app.constants.FailureMessages
import com.cinematica.backend.app.dependencies.AppModule
import com.cinematica.backend.app.services.monitoring.configureMonitoring
import com.cinematica.backend.app.services.serialization.configureSerialization
import com.cinematica.backend.data.authorization.entities.User
import com.cinematica.backend.domain.authorization.routing.configureAuthorizationRouting
import com.cinematica.backend.domain.authorization.types.Test
import com.cinematica.backend.domain.authorization.usecases.signin.SignInUseCase
import com.cinematica.backend.domain.authorization.usecases.signup.SignUpUseCase
import com.cinematica.backend.domain.authorization.usecases.state.AuthorizationStateUseCase
import com.cinematica.backend.foundation.cli.asArguments
import com.cinematica.backend.foundation.cli.getNamedIntOrNull
import com.cinematica.backend.foundation.security.token.data.TokenConfig
import com.typesafe.config.ConfigFactory
import io.ktor.server.application.call
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.Routing
import io.ktor.server.routing.get
import io.ktor.server.routing.routing
import kotlinx.serialization.Serializable
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.insertAndGetId
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction
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

    val password = arguments.getNamedOrNull("PASSWORD")
        ?: System.getenv("PASSWORD")
        ?: error("miss password")

    val user = arguments.getNamedOrNull("USER")
        ?: System.getenv("USER")
        ?: error("miss user")

//    val coroutineClient = KMongo.createClient(databaseUrl).coroutine
//    val database = coroutineClient.getDatabase("cinematica_database")

    val dynamicModule = module {
        // single<CoroutineDatabase> { database }
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

    val mysqlDatabase = Database.connect(
        url = databaseUrl,
        driver = "com.mysql.jdbc.Driver",
        user = user,
        password = password
    )

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

                val a = transaction(mysqlDatabase) {
                    SchemaUtils.create(Users)

                    Users.insertAndGetId {
                        it[username] = test.test
                    }

                    Users.select { Users.username eq test.test }.mapNotNull {
                        Test(test = it[Users.username])
                    }
                }
                call.respond("${a.first()} + somee")
            }
        }
        println("Server started on address: http://127.0.0.1:$port")
    }.start(true)
}

object Users : IntIdTable() {
    val username = varchar("username", 50).uniqueIndex()
}