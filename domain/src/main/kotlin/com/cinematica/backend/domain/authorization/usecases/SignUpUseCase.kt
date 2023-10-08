package com.cinematica.backend.domain.authorization.usecases

import com.cinematica.backend.domain.authorization.repositories.AuthorizationsRepository
import com.cinematica.backend.domain.authorization.types.value.AccessHash
import com.cinematica.backend.domain.authorization.types.value.RefreshHash
import com.cinematica.backend.domain.common.markers.UseCase
import com.cinematica.backend.domain.users.repositories.UsersRepository
import com.cinematica.backend.domain.users.types.value.EmailAddress
import com.cinematica.backend.domain.users.types.value.PasswordHash
import com.cinematica.backend.domain.users.types.value.UserName
import com.cinematica.backend.domain.users.types.value.UserPassword
import com.cinematica.backend.foundation.hashing.HashingRepository
import com.cinematica.backend.foundation.validation.createOrThrowInternally

class SignUpUseCase(
    private val usersRepository: UsersRepository,
    private val authorizationsRepository: AuthorizationsRepository,
    private val hashingRepository: HashingRepository
): UseCase {

    suspend fun execute(
        emailAddress: EmailAddress,
        userName: UserName,
        userPassword: UserPassword
    ): Result {
        val hashedPassword =  hashingRepository.hashPassword(userPassword.string)
        val password = PasswordHash.createOrThrowInternally(hashedPassword)

        val userId = usersRepository.createUser(emailAddress, userName, password)
        val refreshHash = RefreshHash.createOrThrowInternally("abcde_abcde_")
        val accessHash = AccessHash.createOrThrowInternally("s")

        authorizationsRepository.saveRefreshToken(refreshHash, userId)
//        val hashedPassword = PasswordHash.createOrThrowInternally()

        return Result.Success(
            refreshHash = refreshHash,
            accessHash = accessHash
        )
    }

    sealed class Result {
        data class Success(val refreshHash: RefreshHash, val accessHash: AccessHash): Result()
    }
}