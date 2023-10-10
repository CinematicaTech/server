package com.cinematica.backend.domain.authorization.usecases

import com.cinematica.backend.domain.authorization.repositories.AuthorizationsRepository
import com.cinematica.backend.domain.authorization.types.Authorization
import com.cinematica.backend.domain.authorization.types.value.AccessHash
import com.cinematica.backend.domain.common.markers.UseCase
import com.cinematica.backend.foundation.time.TimeProvider
import kotlin.time.Duration.Companion.days

class GetAuthorizationUseCase(
    private val authorizationsRepository: AuthorizationsRepository,
    private val timerProvider: TimeProvider,
) : UseCase {
    suspend fun execute(accessHash: AccessHash): Result {
        return authorizationsRepository.getAuthorization(accessHash, timerProvider.provide() - 7.days)
            ?.let { Result.Success(it) }
            ?: Result.NotFound
    }

    sealed class Result {
        data class Success(val authorization: Authorization) : Result()

        data object NotFound : Result()
    }
}