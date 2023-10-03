package com.cinematica.backend.infrastructure.grpc.users

import com.cinematica.backend.authorization.UsersServiceGrpcKt.UsersServiceCoroutineImplBase
import com.cinematica.backend.authorization.requests.IsUserExistRequestKt
import com.cinematica.backend.authorization.requests.IsUserExistRequestOuterClass.IsUserExistRequest
import com.cinematica.backend.domain.users.types.value.EmailAddress
import com.cinematica.backend.domain.users.usecases.IsUserExistUseCase
import com.cinematica.backend.infrastructure.grpc.common.markers.GrpcService
import com.cinematica.backend.infrastructure.grpc.common.validation.createOrStatus

class UsersService(
    private val isUserExistUseCase: IsUserExistUseCase
): UsersServiceCoroutineImplBase(), GrpcService {

    override suspend fun isUserExist(request: IsUserExistRequest): IsUserExistRequest.Response {
        val email = EmailAddress.createOrStatus(request.emailAddress)

        return when(val result = isUserExistUseCase.execute(email)) {
            is IsUserExistUseCase.Result.Success -> IsUserExistRequestKt.response {
                isUserExist = result.value
            }
        }
    }
}