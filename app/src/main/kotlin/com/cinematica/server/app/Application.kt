@file:Suppress("ExtractKtorModule")

package com.cinematica.server.app

import com.cinematica.backend.foundation.cli.getNamedIntOrNull
import com.cinematica.backend.foundation.cli.parseArguments
import com.cinematica.backend.infrastructure.grpc.example.TestService
import com.cinematica.server.app.constants.ArgumentsConstants
import com.cinematica.server.app.constants.EnvironmentConstants
import com.cinematica.server.app.constants.FailureMessages
import io.grpc.BindableService
import io.grpc.ServerBuilder
import io.grpc.protobuf.services.ProtoReflectionService
import kotlinx.coroutines.coroutineScope
import org.koin.core.context.stopKoin

suspend fun main(args: Array<String>): Unit = coroutineScope {
    val arguments = args.parseArguments()

    val grpcPort = arguments.getNamedIntOrNull(ArgumentsConstants.GRPC_PORT)
        ?: System.getenv(EnvironmentConstants.GRPC_PORT)?.toIntOrNull()
        ?: throw Exception(FailureMessages.MISSING_PORT)

    println("Port: $grpcPort")

    val server = ServerBuilder.forPort(grpcPort)
        .addService(TestService() as BindableService)
        .addService(ProtoReflectionService.newInstance())
        .build()

    server.start()

    Runtime.getRuntime().addShutdownHook(Thread {
        server.shutdown()
        stopKoin()
    })

    server.awaitTermination()
}