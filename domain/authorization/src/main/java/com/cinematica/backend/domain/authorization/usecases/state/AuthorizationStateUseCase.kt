package com.cinematica.backend.domain.authorization.usecases.state

import com.cinematica.backend.domain.authorization.repository.AuthorizationRepository

class AuthorizationStateUseCase(
    private val authorizationRepository: AuthorizationRepository,
) {

    suspend fun execute() {

    }
}