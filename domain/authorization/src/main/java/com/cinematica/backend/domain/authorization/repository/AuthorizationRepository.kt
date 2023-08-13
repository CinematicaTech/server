package com.cinematica.backend.domain.authorization.repository

import com.cinematica.backend.domain.authorization.types.AuthorizationResponse
import com.cinematica.backend.domain.authorization.types.signup.SignUpData
import com.cinematica.backend.domain.authorization.types.signup.SignUpRequest
import com.cinematica.backend.domain.authorization.types.state.AuthorizationStateData
import com.cinematica.backend.domain.authorization.types.state.AuthorizationStateResponse

interface AuthorizationRepository {

    suspend fun signUp(signUpRequest: SignUpRequest): AuthorizationResponse

    suspend fun state(authorizationStateData: AuthorizationStateData): AuthorizationStateResponse
}