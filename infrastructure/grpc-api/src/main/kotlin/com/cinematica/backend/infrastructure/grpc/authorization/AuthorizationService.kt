package com.cinematica.backend.infrastructure.grpc.authorization

import com.cinematica.backend.authorization.AuthorizationServiceGrpcKt.AuthorizationServiceCoroutineImplBase
import com.cinematica.backend.authorization.requests.GetAuthorizationStateRequestOuterClass.GetAuthorizationStateRequest

class AuthorizationService : AuthorizationServiceCoroutineImplBase() {

    override suspend fun getAuthorizationState(
        request: GetAuthorizationStateRequest
    ): GetAuthorizationStateRequest.Result {
        return super.getAuthorizationState(request)
    }
}