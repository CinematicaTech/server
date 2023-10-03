package com.cinematica.backend.domain.authorization.usecases

import com.cinematica.backend.domain.authorization.repositories.AuthorizationsRepository
import com.cinematica.backend.domain.authorization.types.AuthorizationState
import com.cinematica.backend.domain.common.markers.UseCase
import com.cinematica.backend.domain.users.types.value.EmailAddress

class GetAuthorizationStateUseCase(
    private val authorizationsRepository: AuthorizationsRepository
): UseCase {

    suspend fun execute(emailAddress: EmailAddress): Result {
        return Result.Success(authorizationsRepository.getAuthorizationState(emailAddress))
    }

    sealed class Result {
        data class Success(val authorizationState: AuthorizationState): Result()
    }
}