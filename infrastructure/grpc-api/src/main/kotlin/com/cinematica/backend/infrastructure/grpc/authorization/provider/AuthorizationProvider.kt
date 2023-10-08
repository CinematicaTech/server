package com.cinematica.backend.infrastructure.grpc.authorization.provider

import com.cinematica.backend.domain.authorization.types.Authorization
import com.cinematica.backend.domain.authorization.types.value.AccessHash
import com.cinematica.backend.domain.authorization.usecases.GetAuthorizationUseCase

class AuthorizationProvider(
    private val getAuthorization: GetAuthorizationUseCase,
) {
    suspend fun provide(accessHash: AccessHash): Authorization? =
        (getAuthorization.execute(accessHash) as? GetAuthorizationUseCase.Result.Success)?.authorization
}