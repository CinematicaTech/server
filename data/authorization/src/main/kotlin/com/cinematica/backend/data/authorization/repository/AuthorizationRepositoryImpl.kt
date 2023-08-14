package com.cinematica.backend.data.authorization.repository

import com.cinematica.backend.domain.authorization.datasource.AuthorizationDatasourceRepository
import com.cinematica.backend.domain.authorization.mapper.AuthorizationDomainMapper
import com.cinematica.backend.domain.authorization.repository.AuthorizationRepository
import com.cinematica.backend.domain.authorization.types.common.AuthorizationRequest
import com.cinematica.backend.domain.authorization.types.common.AuthorizationResponse
import com.cinematica.backend.domain.authorization.types.state.AuthorizationStateData
import com.cinematica.backend.domain.authorization.types.state.AuthorizationStateResponse
import com.cinematica.backend.foundation.security.hashing.HashingService
import com.cinematica.backend.foundation.security.hashing.data.SaltedHash
import com.cinematica.backend.foundation.security.token.data.TokenClaim
import com.cinematica.backend.foundation.security.token.data.TokenConfig
import com.main.security.token.TokenService

class AuthorizationRepositoryImpl(
    private val authorizationDatasourceRepository: AuthorizationDatasourceRepository,
    private val hashingService: HashingService,
    private val tokenService: TokenService,
    private val authorizationDomainMapper: AuthorizationDomainMapper,
    private val tokenConfig: TokenConfig,
) : AuthorizationRepository {

    override suspend fun signUp(
        authorizationRequest: AuthorizationRequest
    ): Result<AuthorizationResponse> {
        val user = authorizationDatasourceRepository.getUserByEmail(
            authorizationRequest.email
        ).getOrNull()
        if (user != null) {
            return Result.failure(Exception("user is already exist"))
        }
        val saltedHash = hashingService.generateSaltedHash(authorizationRequest.password)
        val signUpData = authorizationDomainMapper.mapToSignUpData(authorizationRequest, saltedHash)
        val token = tokenService.generate(
            config = tokenConfig,
            TokenClaim(
                name = "userId",
                value = signUpData.email
            )
        )
        authorizationDatasourceRepository.insertUser(signUpData)
        return Result.success(AuthorizationResponse(token = token))
    }

    override suspend fun signIn(authorizationRequest: AuthorizationRequest): Result<AuthorizationResponse> {
        val user = authorizationDatasourceRepository.getUserByEmail(
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

    override suspend fun state(
        authorizationStateData: AuthorizationStateData
    ): AuthorizationStateResponse {
        return AuthorizationStateResponse(
            state = authorizationDatasourceRepository.getState(authorizationStateData).value
        )
    }
}