package com.cinematica.backend.domain.authorization.usecases.signin

import com.cinematica.backend.domain.authorization.repository.AuthorizationsRepository
import com.cinematica.backend.domain.authorization.types.authorization.AuthorizationRequest
import com.cinematica.backend.domain.authorization.types.authorization.AuthorizationResponse

class SignInUseCase(
    private val authorizationsRepository: AuthorizationsRepository
) {

    suspend fun execute(authorizationRequest: AuthorizationRequest): Result<AuthorizationResponse> {
        return authorizationsRepository.signIn(authorizationRequest)
    }
}