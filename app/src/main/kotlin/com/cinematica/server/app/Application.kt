@file:Suppress("ExtractKtorModule")

package com.cinematica.server.app

import com.cinematica.backend.foundation.cli.getNamedIntOrNull
import com.cinematica.backend.foundation.cli.parseArguments
import com.cinematica.backend.infrastructure.grpc.authorization.AuthorizationsService
import com.cinematica.backend.infrastructure.grpc.users.UsersService
import com.cinematica.server.app.constants.ArgumentsConstants
import com.cinematica.server.app.constants.EnvironmentConstants
import com.cinematica.server.app.constants.FailureMessages
import com.cinematica.server.app.dependencies.AppModule
import com.cinematica.server.app.dependencies.configuration.DatabaseConfig
import io.grpc.BindableService
import io.grpc.ServerBuilder
import io.grpc.protobuf.services.ProtoReflectionService
import kotlinx.coroutines.coroutineScope
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.dsl.module

suspend fun main(args: Array<String>): Unit = coroutineScope {
    val arguments = args.parseArguments()

    val grpcPort = arguments.getNamedIntOrNull(ArgumentsConstants.GRPC_PORT)
        ?: System.getenv(EnvironmentConstants.GRPC_PORT)?.toIntOrNull()
        ?: error(FailureMessages.MISSING_PORT)

    val databaseUrl = arguments.getNamedOrNull(ArgumentsConstants.DATABASE_URL)
        ?: System.getenv(EnvironmentConstants.DATABASE_URL)
        ?: error(FailureMessages.MISSING_DATABASE_URL)

    val databaseUser = arguments.getNamedOrNull(ArgumentsConstants.DATABASE_USER)
        ?: System.getenv(EnvironmentConstants.DATABASE_USER)
        ?: error(FailureMessages.MISSING_DATABASE_USER)

    val databasePassword = arguments.getNamedOrNull(ArgumentsConstants.DATABASE_PASSWORD)
        ?: System.getenv(EnvironmentConstants.DATABASE_PASSWORD)
        ?: error(FailureMessages.MISSING_DATABASE_PASSWORD)

    val databaseConfig = DatabaseConfig(
        url = databaseUrl,
        user = databaseUser,
        password = databasePassword,
    )

    val dynamicModule = module {
        single<DatabaseConfig> { databaseConfig }
    }

    val koin = startKoin {
        modules(AppModule + dynamicModule)
    }.koin

    val server = ServerBuilder.forPort(grpcPort)
        .addService(koin.get<AuthorizationsService>() as BindableService)
        .addService(koin.get<UsersService>() as BindableService)
        .addService(ProtoReflectionService.newInstance())
        .build()

    server.start()

    Runtime.getRuntime().addShutdownHook(Thread {
        server.shutdown()
        stopKoin()
    })

    server.awaitTermination()
}