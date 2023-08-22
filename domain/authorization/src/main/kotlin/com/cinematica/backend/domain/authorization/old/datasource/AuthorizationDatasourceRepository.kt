package com.cinematica.backend.domain.authorization.old.datasource

import com.cinematica.backend.domain.authorization.old.types.UserDomain
import com.cinematica.backend.domain.authorization.old.types.signup.SignUpData
import com.cinematica.backend.domain.authorization.old.types.state.AuthorizationState
import com.cinematica.backend.domain.authorization.old.types.state.AuthorizationStateData

interface AuthorizationDatasourceRepository {

    suspend fun insertUser(signUpData: SignUpData)

    suspend fun getState(authorizationStateData: AuthorizationStateData): AuthorizationState

    suspend fun getUserByEmail(email: String): Result<UserDomain>
}