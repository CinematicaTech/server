package com.cinematica.backend.domain.authorization.mapper

import com.cinematica.backend.domain.authorization.types.common.AuthorizationRequest
import com.cinematica.backend.domain.authorization.types.signup.SignUpData
import com.cinematica.backend.domain.authorization.types.state.AuthorizationStateData
import com.cinematica.backend.domain.authorization.types.state.AuthorizationStateRequest
import com.cinematica.backend.foundation.security.hashing.data.SaltedHash

class AuthorizationDomainMapper {
    fun mapToSignUpData(authorizationRequest: AuthorizationRequest, salt: SaltedHash): SignUpData {
        return SignUpData(
            email = authorizationRequest.email,
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