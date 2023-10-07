package com.cinematica.backend.domain.authorization.usecases

import com.cinematica.backend.domain.authorization.repositories.AuthorizationsRepository
import com.cinematica.backend.domain.authorization.types.value.RefreshHash
import com.cinematica.backend.domain.common.markers.UseCase
import com.cinematica.backend.domain.users.repositories.UsersRepository
import com.cinematica.backend.domain.users.types.value.EmailAddress
import com.cinematica.backend.domain.users.types.value.PasswordHash
import com.cinematica.backend.domain.users.types.value.UserName
import com.cinematica.backend.foundation.validation.createOrThrowInternally

class ConfigureNewAccountUseCase(
    private val usersRepository: UsersRepository,
    private val authorizationsRepository: AuthorizationsRepository
): UseCase {

    suspend fun execute(
//        emailAddress: EmailAddress,
//        userName: UserName,
//        userPassword: UserPassword
    ) {
        val email = EmailAddress.createOrThrowInternally("vadwwxz@gmail.com")
        val name = UserName.createOrThrowInternally("vadwwxz")
        val password = PasswordHash.createOrThrowInternally("password2004")

        val userId = usersRepository.createUser(email, name, password)

        authorizationsRepository.saveRefreshToken(
            RefreshHash.createOrThrowInternally("abcde_abcde_"),
            userId
        )
//        val hashedPassword = PasswordHash.createOrThrowInternally()


    }
}