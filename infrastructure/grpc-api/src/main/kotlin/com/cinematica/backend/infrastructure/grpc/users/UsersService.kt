package com.cinematica.backend.infrastructure.grpc.users

import com.cinematica.backend.authorization.UsersServiceGrpcKt.UsersServiceCoroutineImplBase
import com.cinematica.backend.infrastructure.grpc.common.markers.GrpcService

class UsersService(
): UsersServiceCoroutineImplBase(), GrpcService {
}