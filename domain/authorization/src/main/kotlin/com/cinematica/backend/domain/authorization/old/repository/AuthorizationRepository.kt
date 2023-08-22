package com.cinematica.backend.domain.authorization.old.repository

import com.cinematica.backend.domain.authorization.old.types.common.AuthorizationRequest
import com.cinematica.backend.domain.authorization.old.types.common.AuthorizationResponse
import com.cinematica.backend.domain.authorization.old.types.state.AuthorizationStateData
import com.cinematica.backend.domain.authorization.old.types.state.AuthorizationStateResponse

interface AuthorizationRepository {

    suspend fun signUp(authorizationRequest: AuthorizationRequest): Result<AuthorizationResponse>

    suspend fun signIn(authorizationRequest: AuthorizationRequest): Result<AuthorizationResponse>

    suspend fun state(authorizationStateData: AuthorizationStateData): AuthorizationStateResponse
}