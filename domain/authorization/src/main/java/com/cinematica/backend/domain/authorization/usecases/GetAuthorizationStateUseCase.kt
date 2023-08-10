package com.cinematica.backend.domain.authorization.usecases

import com.cinematica.backend.domain.authorization.repositories.AuthorizationRepository
import com.cinematica.backend.domain.authorization.types.request.AuthorizationStateRequest
import com.cinematica.backend.domain.authorization.types.response.AuthorizationStateResponse
import com.cinematica.backend.domain.authorization.types.state.AuthorizationState

class GetAuthorizationStateUseCase(
    private val authorizationRepository: AuthorizationRepository
) {
    fun execute(authorizationStateRequest: AuthorizationStateRequest): AuthorizationStateResponse {
        return AuthorizationStateResponse(AuthorizationState.SIGN_IN.value)
    }
}