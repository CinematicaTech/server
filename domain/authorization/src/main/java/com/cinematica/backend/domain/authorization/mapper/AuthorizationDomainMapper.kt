package com.cinematica.backend.domain.authorization.mapper

import com.cinematica.backend.domain.authorization.types.signup.SignUpData
import com.cinematica.backend.domain.authorization.types.signup.SignUpRequest

class AuthorizationDomainMapper {
    fun mapToSignUpData(signUpRequest: SignUpRequest): SignUpData {
        return SignUpData(
            email = signUpRequest.email,
            password = signUpRequest.password
        )
    }
}