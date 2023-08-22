package com.cinematica.backend.domain.authorization.old.usecases.state

import com.cinematica.backend.domain.authorization.old.mapper.AuthorizationDomainMapper
import com.cinematica.backend.domain.authorization.old.repository.AuthorizationRepository
import com.cinematica.backend.domain.authorization.old.types.state.AuthorizationStateRequest
import com.cinematica.backend.domain.authorization.old.types.state.AuthorizationStateResponse

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