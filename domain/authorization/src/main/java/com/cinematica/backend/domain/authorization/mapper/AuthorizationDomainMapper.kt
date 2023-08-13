package com.cinematica.backend.domain.authorization.mapper

import com.cinematica.backend.domain.authorization.types.signup.SignUpData
import com.cinematica.backend.domain.authorization.types.signup.SignUpRequest
import com.cinematica.backend.domain.authorization.types.state.AuthorizationStateData
import com.cinematica.backend.domain.authorization.types.state.AuthorizationStateRequest
import com.cinematica.backend.foundation.security.hashing.data.SaltedHash

class AuthorizationDomainMapper {
    fun mapToSignUpData(signUpRequest: SignUpRequest, salt: SaltedHash): SignUpData {
        return SignUpData(
            email = signUpRequest.email,
            password = salt.hash,
            salt = salt.salt
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