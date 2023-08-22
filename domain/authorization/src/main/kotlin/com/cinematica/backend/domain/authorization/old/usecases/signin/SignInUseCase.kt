package com.cinematica.backend.domain.authorization.old.usecases.signin

import com.cinematica.backend.domain.authorization.old.repository.AuthorizationRepository
import com.cinematica.backend.domain.authorization.old.types.common.AuthorizationRequest
import com.cinematica.backend.domain.authorization.old.types.common.AuthorizationResponse

class SignInUseCase(
    private val authorizationRepository: AuthorizationRepository,
) {

    suspend fun execute(authorizationRequest: AuthorizationRequest): Result<AuthorizationResponse> {
        return authorizationRepository.signIn(authorizationRequest)
    }
}