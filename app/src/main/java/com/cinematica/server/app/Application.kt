@file:Suppress("ExtractKtorModule")

package com.cinematica.server.app

import com.cinematica.backend.infrastructure.grpc.example.TestService
import io.grpc.BindableService
import io.grpc.ServerBuilder
import io.grpc.protobuf.services.ProtoReflectionService
import kotlinx.coroutines.coroutineScope
import org.koin.core.context.stopKoin

suspend fun main(): Unit = coroutineScope {
    val server = ServerBuilder.forPort(8080)
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