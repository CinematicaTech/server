package com.cinematica.backend.data.authorization.mapper

import com.cinematica.backend.data.authorization.entities.User
import com.cinematica.backend.domain.authorization.types.signup.SignUpData
import com.cinematica.backend.domain.authorization.types.signup.SignUpRequest

class AuthorizationDataMapper {
    fun mapToSignUpData(signUpRequest: SignUpRequest): SignUpData {
        return SignUpData(
            email = signUpRequest.email,
            password = signUpRequest.password
        )
    }

    fun mapToUser(signUpRequest: SignUpRequest): User {
        return User(
            email = signUpRequest.email,
            password = signUpRequest.password,
        )
    }

    fun mapToUser(signUpData: SignUpData): User {
        return User(
            email = signUpData.email,
            password = signUpData.password
        )
    }

    fun mapToSignUpRequest(user: User): SignUpRequest {
        return SignUpRequest(
            email = user.email,
            password = user.password
        )
    }
}