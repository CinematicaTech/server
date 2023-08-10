package com.cinematica.backend.domain.authorization.repositories

import com.cinematica.backend.domain.authorization.types.signup.SignUpRequest

interface AuthorizationRepository {

    fun signUp(signUpRequest: SignUpRequest)
}