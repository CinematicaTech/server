package com.cinematica.backend.domain.authorization.usecases.state

import com.cinematica.backend.domain.authorization.repository.AuthorizationsRepository
import com.cinematica.backend.domain.authorization.types.state.AuthorizationState
import com.cinematica.backend.domain.authorization.types.state.AuthorizationStateRequest

class GetAuthorizationStateUseCase(
    private val authorizationsRepository: AuthorizationsRepository
) {

    suspend fun execute(authorizationStateRequest: AuthorizationStateRequest): AuthorizationState {
        return authorizationsRepository.state(authorizationStateRequest)
    }
}