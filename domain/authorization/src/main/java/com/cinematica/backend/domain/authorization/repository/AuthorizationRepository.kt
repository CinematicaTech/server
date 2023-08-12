package com.cinematica.backend.domain.authorization.repository

import com.cinematica.backend.domain.authorization.types.SuccessfulAuthorizationResponse
import com.cinematica.backend.domain.authorization.types.signup.SignUpData
import com.cinematica.backend.domain.authorization.types.state.AuthorizationState
import com.cinematica.backend.domain.authorization.types.state.AuthorizationStateData
import com.cinematica.backend.domain.authorization.types.state.AuthorizationStateResponse

interface AuthorizationRepository {

    suspend fun signUp(signUpData: SignUpData): SuccessfulAuthorizationResponse

    suspend fun state(authorizationStateData: AuthorizationStateData): AuthorizationStateResponse
}