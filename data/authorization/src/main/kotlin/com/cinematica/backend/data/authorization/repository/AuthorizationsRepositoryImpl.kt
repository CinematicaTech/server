package com.cinematica.backend.data.authorization.repository

import com.cinematica.backend.data.authorization.mapper.AuthorizationsMapper
import com.cinematica.backend.domain.authorization.datasource.AuthorizationsDataSourceRepository
import com.cinematica.backend.domain.authorization.repository.AuthorizationsRepository
import com.cinematica.backend.domain.authorization.types.authorization.AuthorizationRequest
import com.cinematica.backend.domain.authorization.types.authorization.AuthorizationResponse
import com.cinematica.backend.foundation.security.hashing.HashingService
import com.cinematica.backend.foundation.security.token.data.TokenClaim
import com.cinematica.backend.foundation.security.token.data.TokenConfig
import com.main.security.token.TokenService

class AuthorizationsRepositoryImpl(
    private val authorizationsDataSourceRepository: AuthorizationsDataSourceRepository,
    private val hashingService: HashingService,
    private val tokenService: TokenService,
    private val tokenConfig: TokenConfig,
    private val authorizationsMapper: AuthorizationsMapper
) : AuthorizationsRepository {

    override suspend fun signUp(
        authorizationRequest: AuthorizationRequest
    ): Result<AuthorizationResponse> {
        println("AuthRepoImpl: 1")
        val saltedHash = hashingService.generateSaltedHash(authorizationRequest.password)
        val userData = authorizationsMapper.toUserData(authorizationRequest, saltedHash)
        val token = tokenService.generate(
            config = tokenConfig,
            TokenClaim(
                name = "userId",
                value = userData.email
            )
        )
        println("AuthRepoImpl: 2")
        return try {
            authorizationsDataSourceRepository.createAccount(userData)
            Result.success(AuthorizationResponse(token = token))
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}