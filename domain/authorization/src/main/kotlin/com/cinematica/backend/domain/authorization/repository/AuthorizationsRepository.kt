package com.cinematica.backend.domain.authorization.repository

import com.cinematica.backend.domain.authorization.types.authorization.AuthorizationRequest
import com.cinematica.backend.domain.authorization.types.authorization.AuthorizationResponse
import com.cinematica.backend.domain.authorization.types.state.AuthorizationState
import com.cinematica.backend.domain.authorization.types.state.AuthorizationStateRequest

interface AuthorizationsRepository {

    suspend fun signUp(authorizationRequest: AuthorizationRequest): Result<AuthorizationResponse>

    suspend fun signIn(authorizationRequest: AuthorizationRequest): Result<AuthorizationResponse>

    suspend fun state(authorizationStateRequest: AuthorizationStateRequest): AuthorizationState
}