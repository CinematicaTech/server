package com.cinematica.backend.domain.authorization.datasource

import com.cinematica.backend.domain.authorization.types.signup.SignUpData
import com.cinematica.backend.domain.authorization.types.state.AuthorizationState
import com.cinematica.backend.domain.authorization.types.state.AuthorizationStateData

interface AuthorizationDatasourceRepository {

    suspend fun insertUser(signUpData: SignUpData)

    suspend fun getState(authorizationStateData: AuthorizationStateData): AuthorizationState
}