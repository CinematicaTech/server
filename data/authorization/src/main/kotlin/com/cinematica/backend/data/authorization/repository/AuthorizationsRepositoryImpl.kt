package com.cinematica.backend.data.authorization.repository

import com.cinematica.backend.data.authorization.mapper.AuthorizationsMapper
import com.cinematica.backend.domain.authorization.datasource.AuthorizationsDataSourceRepository
import com.cinematica.backend.domain.authorization.repository.AuthorizationsRepository
import com.cinematica.backend.domain.authorization.types.authorization.AuthorizationRequest
import com.cinematica.backend.domain.authorization.types.authorization.AuthorizationResponse
import com.cinematica.backend.foundation.security.hashing.HashingService
import com.cinematica.backend.foundation.security.hashing.data.SaltedHash
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
        val saltedHash = hashingService.generateSaltedHash(authorizationRequest.password)
        val userData = authorizationsMapper.toUserData(authorizationRequest, saltedHash)
        val token = tokenService.generate(
            config = tokenConfig,
            TokenClaim(
                name = "userId",
                value = userData.email
            )
        )
        return try {
            authorizationsDataSourceRepository.createAccount(userData)
            Result.success(AuthorizationResponse(token = token))
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun signIn(authorizationRequest: AuthorizationRequest): Result<AuthorizationResponse> {
        val user = authorizationsDataSourceRepository.getUserByEmail(
            authorizationRequest.email
        ).getOrNull() ?: return Result.failure(Exception("user is null"))

        val isValidPassword = hashingService.verify(
            value = authorizationRequest.password,
            saltedHash = SaltedHash(
                hash = user.password,
                salt = user.salt
            )
        )


        return if (!isValidPassword) {
            return Result.failure(Exception("password is wrong"))
        } else {
            val token = tokenService.generate(
                config = tokenConfig,
                TokenClaim(
                    name = "userId",
                    value = user.email
                )
            )
            Result.success(AuthorizationResponse(token))
        }
    }
}