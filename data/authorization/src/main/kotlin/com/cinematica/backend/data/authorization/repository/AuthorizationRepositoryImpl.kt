package com.cinematica.backend.data.authorization.repository

import com.cinematica.backend.domain.authorization.datasource.AuthorizationDatasourceRepository
import com.cinematica.backend.domain.authorization.mapper.AuthorizationDomainMapper
import com.cinematica.backend.domain.authorization.repository.AuthorizationRepository
import com.cinematica.backend.domain.authorization.types.AuthorizationResponse
import com.cinematica.backend.domain.authorization.types.signup.SignUpRequest
import com.cinematica.backend.domain.authorization.types.state.AuthorizationStateData
import com.cinematica.backend.domain.authorization.types.state.AuthorizationStateResponse
import com.cinematica.backend.foundation.security.hashing.HashingService

class AuthorizationRepositoryImpl(
    private val authorizationDatasourceRepository: AuthorizationDatasourceRepository,
    private val hashingService: HashingService,
    private val authorizationDomainMapper: AuthorizationDomainMapper
) : AuthorizationRepository {

    override suspend fun signUp(signUpRequest: SignUpRequest): AuthorizationResponse {
        val saltedHash = hashingService.generateSaltedHash(signUpRequest.password)
        val signUpData = authorizationDomainMapper.mapToSignUpData(signUpRequest, saltedHash)
        val result = authorizationDatasourceRepository.insertUser(signUpData)
        return AuthorizationResponse(
            token = ""
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