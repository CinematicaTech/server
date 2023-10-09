package com.cinematica.backend.domain.authorization.usecases

import com.cinematica.backend.domain.authorization.types.AuthorizationMethod
import com.cinematica.backend.domain.authorization.types.AuthorizationState
import com.cinematica.backend.domain.common.markers.UseCase
import com.cinematica.backend.domain.users.repositories.UsersRepository
import com.cinematica.backend.domain.users.types.value.EmailAddress

class GetAuthorizationStateUseCase(
    private val usersRepository: UsersRepository
): UseCase {

    suspend fun execute(emailAddress: EmailAddress): Result {
        val result = usersRepository.isUserExist(emailAddress)
        return if (result) {
            Result.Success(AuthorizationState(AuthorizationMethod.SIGN_IN.value))
        } else {
            Result.Success(AuthorizationState(AuthorizationMethod.SIGN_UP.value))
        }
    }

    sealed class Result {
        data class Success(val authorizationState: AuthorizationState): Result()
    }
}