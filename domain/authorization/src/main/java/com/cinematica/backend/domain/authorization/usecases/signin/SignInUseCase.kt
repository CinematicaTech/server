package com.cinematica.backend.domain.authorization.usecases.signin

import com.cinematica.backend.domain.authorization.repository.AuthorizationRepository
import com.cinematica.backend.domain.authorization.types.common.AuthorizationRequest
import com.cinematica.backend.domain.authorization.types.common.AuthorizationResponse

class SignInUseCase(
    private val authorizationRepository: AuthorizationRepository,
) {

    suspend fun execute(authorizationRequest: AuthorizationRequest): Result<AuthorizationResponse> {
        return authorizationRepository.signIn(authorizationRequest)
    }
}