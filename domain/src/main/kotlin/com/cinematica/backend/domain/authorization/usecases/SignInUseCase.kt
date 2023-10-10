package com.cinematica.backend.domain.authorization.usecases

import com.cinematica.backend.domain.authorization.repositories.AuthorizationsRepository
import com.cinematica.backend.domain.common.markers.UseCase
import com.cinematica.backend.domain.users.types.value.EmailAddress
import com.cinematica.backend.domain.users.types.value.UserPassword

class SignInUseCase(
    private val authorizationsRepository: AuthorizationsRepository
): UseCase {

    suspend fun execute(
        emailAddress: EmailAddress,
        userPassword: UserPassword,
    ) {

    }
}