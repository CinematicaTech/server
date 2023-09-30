package com.cinematica.backend.infrastructure.grpc.example

import com.cinematica.backend.example.TestServiceGrpcKt
import com.cinematica.backend.example.TestServiceOuterClass.SomeTestData

class TestService : TestServiceGrpcKt.TestServiceCoroutineImplBase() {

    override suspend fun testRequestKotlin(request: SomeTestData): SomeTestData {
        return SomeTestData.newBuilder().setName(request.name.toString()).build()
    }
}