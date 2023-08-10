package com.cinematica.backend.domain.authorization.repository

import com.cinematica.backend.domain.authorization.types.signup.SignUpData

interface AuthorizationRepository {

    suspend fun signUp(signUpData: SignUpData)
}