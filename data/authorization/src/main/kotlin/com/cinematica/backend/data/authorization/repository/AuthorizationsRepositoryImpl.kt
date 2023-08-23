package com.cinematica.backend.data.authorization.repository

import com.cinematica.backend.domain.authorization.datasource.AuthorizationsDataSourceRepository
import com.cinematica.backend.domain.authorization.repository.AuthorizationsRepository
import com.cinematica.backend.domain.authorization.types.authorization.AuthorizationRequest
import com.cinematica.backend.domain.authorization.types.authorization.AuthorizationResponse
import com.cinematica.backend.foundation.security.hashing.HashingService
import com.cinematica.backend.foundation.security.token.data.TokenConfig
import com.main.security.token.TokenService

class AuthorizationsRepositoryImpl(
    private val authorizationsDataSourceRepository: AuthorizationsDataSourceRepository,
    private val hashingService: HashingService,
    private val tokenService: TokenService,
    private val tokenConfig: TokenConfig,
) : AuthorizationsRepository {
    override suspend fun signUp(
        authorizationRequest: AuthorizationRequest
    ): Result<AuthorizationResponse> {
        TODO("Not yet implemented")
    }
}