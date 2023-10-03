package com.cinematica.backend.domain.users.usecases

import com.cinematica.backend.domain.common.markers.UseCase
import com.cinematica.backend.domain.users.repositories.UsersRepository
import com.cinematica.backend.domain.users.types.value.EmailAddress

class IsUserExistUseCase(
    private val usersRepository: UsersRepository
) : UseCase {

    suspend fun execute(emailAddress: EmailAddress): Result {
        val result = usersRepository.isUserExists(emailAddress)
        return Result.Success(result)
    }

    sealed interface Result {
        data class Success(val value: Boolean): Result
    }
}