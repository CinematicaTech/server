package com.cinematica.backend.infrastructure.grpc.users

import com.cinematica.backend.authorization.UsersServiceGrpcKt.UsersServiceCoroutineImplBase
import com.cinematica.backend.domain.users.types.UsersScope
import com.cinematica.backend.infrastructure.grpc.authorization.context.provideAuthorizationContext
import com.cinematica.backend.infrastructure.grpc.common.markers.GrpcService
import com.google.protobuf.Empty

class UsersService(
): UsersServiceCoroutineImplBase(), GrpcService {

    override suspend fun somethingTest(request: Empty): Empty = provideAuthorizationContext<UsersScope, Empty>{
        Empty.getDefaultInstance()
    }
}