package com.cinematica.backend.infrastructure.grpc.authorization

import com.cinematica.backend.authorization.AuthorizationServiceGrpcKt.AuthorizationServiceCoroutineImplBase
import com.cinematica.backend.authorization.requests.GetAuthorizationStateRequestKt
import com.cinematica.backend.authorization.requests.GetAuthorizationStateRequestOuterClass.GetAuthorizationStateRequest
import com.cinematica.backend.authorization.requests.SignUpRequestKt
import com.cinematica.backend.authorization.requests.SignUpRequestOuterClass.SignUpRequest
import com.cinematica.backend.domain.authorization.types.Authorization
import com.cinematica.backend.domain.authorization.types.metadata.ClientMetadata
import com.cinematica.backend.domain.authorization.types.metadata.value.ClientIpAddress
import com.cinematica.backend.domain.authorization.types.metadata.value.ClientName
import com.cinematica.backend.domain.authorization.types.metadata.value.ClientVersion
import com.cinematica.backend.domain.authorization.usecases.SignUpUseCase
import com.cinematica.backend.domain.authorization.usecases.GetAuthorizationStateUseCase
import com.cinematica.backend.domain.users.types.value.EmailAddress
import com.cinematica.backend.domain.users.types.value.UserName
import com.cinematica.backend.domain.users.types.value.UserPassword
import com.cinematica.backend.infrastructure.grpc.authorization.interceptor.SessionContext
import com.cinematica.backend.infrastructure.grpc.authorization.mapper.GrpcAuthorizationsMapper
import com.cinematica.backend.infrastructure.grpc.common.markers.GrpcService
import com.cinematica.backend.infrastructure.grpc.common.validation.createOrStatus
import kotlin.coroutines.coroutineContext

class AuthorizationsService(
    private val getAuthorizationStateUseCase: GetAuthorizationStateUseCase,
    private val signUpUseCase: SignUpUseCase,
    private val mapper: GrpcAuthorizationsMapper
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

        val metadata = ClientMetadata(
            clientName = ClientName.createOrStatus(request.metadata.clientName),
            clientVersion = ClientVersion.createOrStatus(request.metadata.clientVersion),
            clientIpAddress = coroutineContext[SessionContext]!!.ipAddress,
        )

        return when (val result = signUpUseCase.execute(email, username, password, metadata)) {
            is SignUpUseCase.Result.Success -> {
                SignUpRequestKt.response {
                    authorization = mapper.toGrpcAuthorization(result.authorization)
                }
            }
        }
    }
}