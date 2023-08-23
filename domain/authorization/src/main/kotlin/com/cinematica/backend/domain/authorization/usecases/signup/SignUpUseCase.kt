package com.cinematica.backend.domain.authorization.usecases.signup

import com.cinematica.backend.domain.authorization.repository.AuthorizationsRepository
import com.cinematica.backend.domain.authorization.types.authorization.AuthorizationRequest
import com.cinematica.backend.domain.authorization.types.authorization.AuthorizationResponse

class SignUpUseCase(
    private val authorizationsRepository: AuthorizationsRepository
) {

    suspend fun execute(authorizationRequest: AuthorizationRequest): Result<AuthorizationResponse> {
        return authorizationsRepository.signUp(authorizationRequest)
    }
}