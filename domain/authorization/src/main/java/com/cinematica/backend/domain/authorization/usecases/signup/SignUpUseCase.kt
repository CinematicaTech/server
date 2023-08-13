package com.cinematica.backend.domain.authorization.usecases.signup

import com.cinematica.backend.domain.authorization.repository.AuthorizationRepository
import com.cinematica.backend.domain.authorization.types.common.AuthorizationRequest
import com.cinematica.backend.domain.authorization.types.common.AuthorizationResponse

class SignUpUseCase(
    private val authorizationRepository: AuthorizationRepository,
) {
    suspend fun execute(authorizationRequest: AuthorizationRequest): AuthorizationResponse {
        return authorizationRepository.signUp(authorizationRequest)
    }
}