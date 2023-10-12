package com.cinematica.backend.domain.authorization.usecases

import com.cinematica.backend.domain.authorization.repositories.AuthorizationsRepository
import com.cinematica.backend.domain.authorization.types.Authorization
import com.cinematica.backend.domain.common.markers.UseCase
import com.cinematica.backend.domain.users.repositories.UsersRepository
import com.cinematica.backend.domain.users.types.value.EmailAddress
import com.cinematica.backend.domain.users.types.value.UserPassword
import com.cinematica.backend.foundation.hashing.HashingRepository

class SignInUseCase(
    private val usersRepository: UsersRepository,
    private val hashingRepository: HashingRepository,
    private val authorizationsRepository: AuthorizationsRepository
) : UseCase {

    suspend fun execute(
        emailAddress: EmailAddress,
        userPassword: UserPassword,
    ): Result {
        val password = userPassword.string
        val user = usersRepository.getUser(emailAddress) ?: return Result.NotFound
        val hashedPassword = user.password.hash
        val resultOfValidation = hashingRepository.validPassword(password, hashedPassword)

        return if (resultOfValidation) {
            val authorization = authorizationsRepository.getAuthorizationByUserId(user.userId)
            authorization?.let { Result.Success(it) } ?: Result.NotFound
        } else {
            Result.PasswordIsWrong
        }
    }

    sealed class Result {
        data class Success(val authorization: Authorization) : Result()
        data object PasswordIsWrong : Result()
        data object NotFound : Result()
    }
}