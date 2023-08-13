package com.cinematica.backend.domain.authorization.usecases.signin

import com.cinematica.backend.domain.authorization.types.common.AuthorizationRequest
import com.cinematica.backend.domain.authorization.types.common.AuthorizationResponse

class SignInUseCase {

    suspend fun execute(authorizationRequest: AuthorizationRequest): AuthorizationResponse {
        return AuthorizationResponse("")
    }
}