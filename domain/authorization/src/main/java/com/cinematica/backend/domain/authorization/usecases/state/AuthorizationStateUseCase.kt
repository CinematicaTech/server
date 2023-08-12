package com.cinematica.backend.domain.authorization.usecases.state

import com.cinematica.backend.domain.authorization.mapper.AuthorizationDomainMapper
import com.cinematica.backend.domain.authorization.repository.AuthorizationRepository
import com.cinematica.backend.domain.authorization.types.state.AuthorizationStateRequest
import com.cinematica.backend.domain.authorization.types.state.AuthorizationStateResponse

class AuthorizationStateUseCase(
    private val authorizationRepository: AuthorizationRepository,
    private val authorizationDomainMapper: AuthorizationDomainMapper
) {

    suspend fun execute(
        authorizationStateRequest: AuthorizationStateRequest
    ): AuthorizationStateResponse {
        val authorizationStateData = authorizationDomainMapper.mapToAuthorizationStateData(
            authorizationStateRequest = authorizationStateRequest
        )
        return authorizationRepository.state(authorizationStateData)
    }
}