package com.cinematica.backend.domain.authorization.usecases

import com.cinematica.backend.domain.common.markers.UseCase
import com.cinematica.backend.domain.users.repositories.UsersRepository
import com.cinematica.backend.domain.users.types.value.EmailAddress
import com.cinematica.backend.domain.users.types.value.PasswordHash
import com.cinematica.backend.domain.users.types.value.UserPassword
import com.cinematica.backend.foundation.hashing.HashingRepository
import com.cinematica.backend.foundation.validation.createOrThrowInternally

class SignInUseCase(
    private val usersRepository: UsersRepository,
    private val hashingRepository: HashingRepository,
): UseCase {

    suspend fun execute(
        emailAddress: EmailAddress,
        userPassword: UserPassword,
    ) {
        val hashedPassword = hashingRepository.hashPassword(userPassword.string)
        val password = PasswordHash.createOrThrowInternally(hashedPassword)

        val result = usersRepository.getUser(emailAddress, password)
        val resultOfValidation = hashingRepository.validPassword(password.hash, result?.password?.hash.toString())
        println(result)
        println(resultOfValidation)
    }

    sealed class Result {
        //data class Success(val authorization: Authorization) : Result()

        data object NotFound : Result()
    }
}