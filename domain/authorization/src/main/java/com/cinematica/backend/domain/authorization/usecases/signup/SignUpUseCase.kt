package com.cinematica.backend.domain.authorization.usecases.signup

import com.cinematica.backend.domain.authorization.repository.AuthorizationRepository
import com.cinematica.backend.domain.authorization.types.AuthorizationResponse
import com.cinematica.backend.domain.authorization.types.signup.SignUpRequest

class SignUpUseCase(
    private val authorizationRepository: AuthorizationRepository,
) {
    suspend fun execute(signUpRequest: SignUpRequest): AuthorizationResponse {
        return authorizationRepository.signUp(signUpRequest)
    }
}