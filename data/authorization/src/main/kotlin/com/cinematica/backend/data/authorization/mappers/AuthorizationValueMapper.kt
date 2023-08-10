package com.cinematica.backend.data.authorization.mappers

import com.cinematica.backend.domain.authorization.types.signup.SignUpRequest
import com.cinematica.backend.domain.authorization.types.value.EmailAddress
import com.cinematica.backend.domain.authorization.types.value.SignUpRequestValue
import com.cinematica.backend.foundation.validation.createOrThrow
import kotlin.math.sign

class AuthorizationValueMapper {
    fun toSignUpRequestValue(signUpRequest: SignUpRequest): SignUpRequestValue {
        return SignUpRequestValue(
            email = EmailAddress.createOrThrow(signUpRequest.email)
        )
    }
}