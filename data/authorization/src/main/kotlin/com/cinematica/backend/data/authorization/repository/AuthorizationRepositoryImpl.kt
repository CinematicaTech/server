package com.cinematica.backend.data.authorization.repository

import com.cinematica.backend.domain.authorization.datasource.AuthorizationDatasourceRepository
import com.cinematica.backend.domain.authorization.repository.AuthorizationRepository
import com.cinematica.backend.domain.authorization.types.signup.SignUpData
import com.cinematica.backend.domain.authorization.types.state.AuthorizationState
import com.cinematica.backend.domain.authorization.types.state.AuthorizationStateData

class AuthorizationRepositoryImpl(
    private val authorizationDatasourceRepository: AuthorizationDatasourceRepository
) : AuthorizationRepository {

    override suspend fun signUp(signUpData: SignUpData) {
        authorizationDatasourceRepository.insertUser(signUpData)
    }

    override suspend fun state(authorizationStateData: AuthorizationStateData): AuthorizationState {
        return authorizationDatasourceRepository.getState(authorizationStateData)
    }
}