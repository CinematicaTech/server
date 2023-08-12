package com.cinematica.backend.domain.authorization.mapper

import com.cinematica.backend.domain.authorization.types.signup.SignUpData
import com.cinematica.backend.domain.authorization.types.signup.SignUpRequest
import com.cinematica.backend.domain.authorization.types.state.AuthorizationStateData
import com.cinematica.backend.domain.authorization.types.state.AuthorizationStateRequest

class AuthorizationDomainMapper {
    fun mapToSignUpData(signUpRequest: SignUpRequest): SignUpData {
        return SignUpData(
            email = signUpRequest.email,
            password = signUpRequest.password
        )
    }

    fun mapToAuthorizationStateData(
        authorizationStateRequest: AuthorizationStateRequest
    ): AuthorizationStateData {
        return AuthorizationStateData(
            email = authorizationStateRequest.email
        )
    }
}