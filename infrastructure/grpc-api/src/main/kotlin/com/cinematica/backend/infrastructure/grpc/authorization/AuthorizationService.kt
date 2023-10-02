package com.cinematica.backend.infrastructure.grpc.authorization

import com.cinematica.backend.authorization.AuthorizationServiceGrpcKt.AuthorizationServiceCoroutineImplBase
import com.cinematica.backend.authorization.requests.GetAuthorizationStateRequestKt
import com.cinematica.backend.authorization.requests.GetAuthorizationStateRequestOuterClass.GetAuthorizationStateRequest
import com.cinematica.backend.domain.users.types.value.EmailAddress
import com.cinematica.backend.domain.authorization.usecases.GetAuthorizationStateUseCase
import com.cinematica.backend.infrastructure.grpc.common.markers.GrpcService
import com.cinematica.backend.infrastructure.grpc.common.validation.createOrStatus

class AuthorizationService(
    private val getAuthorizationStateUseCase: GetAuthorizationStateUseCase
) : AuthorizationServiceCoroutineImplBase(), GrpcService {

    override suspend fun getAuthorizationState(
        request: GetAuthorizationStateRequest
    ): GetAuthorizationStateRequest.Response {
        val email = EmailAddress.createOrStatus(request.emailAddress)

        return when (val result = getAuthorizationStateUseCase.execute(email)) {
            is GetAuthorizationStateUseCase.Result.Success -> GetAuthorizationStateRequestKt.response {
                authorizationMethod = result.authorizationState.state
            }
        }
    }
}