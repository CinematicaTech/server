package com.cinematica.backend.data.authorization.mapper

import com.cinematica.backend.data.authorization.entities.User
import com.cinematica.backend.domain.authorization.types.signup.SignUpData

class AuthorizationDataMapper {
    fun mapToUser(signUpData: SignUpData): User {
        return User(
            email = signUpData.email,
            password = signUpData.password,
            salt = signUpData.salt
        )
    }
}