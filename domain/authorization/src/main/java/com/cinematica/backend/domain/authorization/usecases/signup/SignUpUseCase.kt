package com.cinematica.backend.domain.authorization.usecases.signup

import com.cinematica.backend.domain.authorization.mapper.AuthorizationDomainMapper
import com.cinematica.backend.domain.authorization.repository.AuthorizationRepository
import com.cinematica.backend.domain.authorization.types.SuccessfulAuthorizationResponse
import com.cinematica.backend.domain.authorization.types.signup.SignUpRequest

class SignUpUseCase(
    private val authorizationRepository: AuthorizationRepository,
    private val authorizationDomainMapper: AuthorizationDomainMapper
) {
    suspend fun execute(signUpRequest: SignUpRequest): SuccessfulAuthorizationResponse {
        val signUpData = authorizationDomainMapper.mapToSignUpData(signUpRequest)
        return authorizationRepository.signUp(signUpData)
    }
}