package com.cinematica.backend.domain.authorization.usecases

import com.cinematica.backend.domain.authorization.repositories.AuthorizationRepository
import com.cinematica.backend.domain.authorization.types.state.AuthorizationState
import com.cinematica.backend.domain.authorization.types.state.AuthorizationStateRequest

class GetAuthorizationStateUseCase(
    private val authorizationRepository: AuthorizationRepository
) {
    fun execute(authorizationStateRequest: AuthorizationStateRequest): AuthorizationState {
        return AuthorizationState.SIGN_IN
    }
}