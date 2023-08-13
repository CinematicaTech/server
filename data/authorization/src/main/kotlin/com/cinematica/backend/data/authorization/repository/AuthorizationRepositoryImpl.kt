package com.cinematica.backend.data.authorization.repository

import com.cinematica.backend.domain.authorization.datasource.AuthorizationDatasourceRepository
import com.cinematica.backend.domain.authorization.mapper.AuthorizationDomainMapper
import com.cinematica.backend.domain.authorization.repository.AuthorizationRepository
import com.cinematica.backend.domain.authorization.types.AuthorizationResponse
import com.cinematica.backend.domain.authorization.types.signup.SignUpRequest
import com.cinematica.backend.domain.authorization.types.state.AuthorizationStateData
import com.cinematica.backend.domain.authorization.types.state.AuthorizationStateResponse
import com.cinematica.backend.foundation.security.hashing.HashingService
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

    override suspend fun signUp(signUpRequest: SignUpRequest): AuthorizationResponse {
        val saltedHash = hashingService.generateSaltedHash(signUpRequest.password)
        val signUpData = authorizationDomainMapper.mapToSignUpData(signUpRequest, saltedHash)
        val token = tokenService.generate(
            config = tokenConfig,
            TokenClaim(
                name = "userId",
                value = signUpData.email
            )
        )
        authorizationDatasourceRepository.insertUser(signUpData)
        return AuthorizationResponse(
            token = token
        )
    }

    override suspend fun state(
        authorizationStateData: AuthorizationStateData
    ): AuthorizationStateResponse {
        return AuthorizationStateResponse(
            state = authorizationDatasourceRepository.getState(authorizationStateData).value
        )
    }
}