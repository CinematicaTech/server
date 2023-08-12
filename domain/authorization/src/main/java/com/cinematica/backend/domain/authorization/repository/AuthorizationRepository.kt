package com.cinematica.backend.domain.authorization.repository

import com.cinematica.backend.domain.authorization.types.signup.SignUpData
import com.cinematica.backend.domain.authorization.types.state.AuthorizationState
import com.cinematica.backend.domain.authorization.types.state.AuthorizationStateData

interface AuthorizationRepository {

    suspend fun signUp(signUpData: SignUpData)

    suspend fun state(authorizationStateData: AuthorizationStateData): AuthorizationState
}