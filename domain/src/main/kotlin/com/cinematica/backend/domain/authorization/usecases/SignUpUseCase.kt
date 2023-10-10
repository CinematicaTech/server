package com.cinematica.backend.domain.authorization.usecases

import com.cinematica.backend.domain.authorization.repositories.AuthorizationsRepository
import com.cinematica.backend.domain.authorization.types.Authorization
import com.cinematica.backend.domain.authorization.types.metadata.ClientMetadata
import com.cinematica.backend.domain.authorization.types.value.AccessHash
import com.cinematica.backend.domain.authorization.types.value.RefreshHash
import com.cinematica.backend.domain.common.markers.UseCase
import com.cinematica.backend.domain.users.repositories.UsersRepository
import com.cinematica.backend.domain.users.types.value.EmailAddress
import com.cinematica.backend.domain.users.types.value.PasswordHash
import com.cinematica.backend.domain.users.types.value.UserName
import com.cinematica.backend.domain.users.types.value.UserPassword
import com.cinematica.backend.foundation.authorization.Scope
import com.cinematica.backend.foundation.hashing.HashingRepository
import com.cinematica.backend.foundation.random.RandomProvider
import com.cinematica.backend.foundation.time.TimeProvider
import com.cinematica.backend.foundation.validation.createOrThrowInternally
import kotlin.time.Duration.Companion.days

class SignUpUseCase(
    private val usersRepository: UsersRepository,
    private val authorizationsRepository: AuthorizationsRepository,
    private val hashingRepository: HashingRepository,
    private val timeProvider: TimeProvider,
    private val randomProvider: RandomProvider
): UseCase {

    suspend fun execute(
        emailAddress: EmailAddress,
        userName: UserName,
        userPassword: UserPassword,
        clientMetadata: ClientMetadata
    ): Result {
        val isUserExist = usersRepository.isUserExist(emailAddress)
        if (isUserExist) return Result.UserAlreadyExist

        val hashedPassword =  hashingRepository.hashPassword(userPassword.string)
        val password = PasswordHash.createOrThrowInternally(hashedPassword)
        val userId = usersRepository.createUser(emailAddress, userName, password)

        val refreshHash = RefreshHash.createOrThrowInternally(randomProvider.randomHash(RefreshHash.SIZE))
        val accessHash = AccessHash.createOrThrowInternally(randomProvider.randomHash(AccessHash.SIZE))
        val creationTime = timeProvider.provide()
        val expiresAt = creationTime + 30.days

        authorizationsRepository.createAccount(
            userId, refreshHash, accessHash, expiresAt, creationTime, clientMetadata
        )

        return Result.Success(
            Authorization(
                userId = userId,
                accessHash = accessHash,
                refreshAccessHash = refreshHash,
                scopes = listOf(Scope.All),
                expiresAt = expiresAt,
                createdAt = creationTime,
                clientMetadata = clientMetadata
            )
        )
    }

    sealed class Result {
        data class Success(val authorization: Authorization): Result()
        data object UserAlreadyExist: Result()
    }
}