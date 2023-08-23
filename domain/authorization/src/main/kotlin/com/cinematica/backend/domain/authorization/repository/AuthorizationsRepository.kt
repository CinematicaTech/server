package com.cinematica.backend.domain.authorization.repository

import com.cinematica.backend.domain.authorization.types.authorization.AuthorizationRequest
import com.cinematica.backend.domain.authorization.types.authorization.AuthorizationResponse

interface AuthorizationsRepository {

    suspend fun signUp(authorizationRequest: AuthorizationRequest): Result<AuthorizationResponse>
}