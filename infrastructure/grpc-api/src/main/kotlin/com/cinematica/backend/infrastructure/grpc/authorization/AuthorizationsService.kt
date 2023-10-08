package com.cinematica.backend.infrastructure.grpc.authorization

import com.cinematica.backend.authorization.AuthorizationServiceGrpcKt.AuthorizationServiceCoroutineImplBase
import com.cinematica.backend.authorization.requests.GetAuthorizationStateRequestKt
import com.cinematica.backend.authorization.requests.GetAuthorizationStateRequestOuterClass.GetAuthorizationStateRequest
import com.cinematica.backend.authorization.requests.SignUpRequestKt
import com.cinematica.backend.authorization.requests.SignUpRequestOuterClass.SignUpRequest
import com.cinematica.backend.domain.authorization.usecases.SignUpUseCase
import com.cinematica.backend.domain.authorization.usecases.GetAuthorizationStateUseCase
import com.cinematica.backend.domain.users.types.value.EmailAddress
import com.cinematica.backend.domain.users.types.value.UserName
import com.cinematica.backend.domain.users.types.value.UserPassword
import com.cinematica.backend.infrastructure.grpc.common.markers.GrpcService
import com.cinematica.backend.infrastructure.grpc.common.validation.createOrStatus

class AuthorizationsService(
    private val getAuthorizationStateUseCase: GetAuthorizationStateUseCase,
    private val signUpUseCase: SignUpUseCase
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

    override suspend fun signUp(
        request: SignUpRequest
    ): SignUpRequest.Response {
        val email = EmailAddress.createOrStatus(request.emailAddress)
        val password = UserPassword.createOrStatus(request.password)
        val username = UserName.createOrStatus(request.username)

        return when (val result = signUpUseCase.execute(email, username, password)) {
            else -> {
                SignUpRequestKt.response {
                    refreshToken = ""
                    accessToken =  ""
                }
            }
        }
    }
}