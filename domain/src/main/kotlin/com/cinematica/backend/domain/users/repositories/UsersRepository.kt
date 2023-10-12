package com.cinematica.backend.domain.users.repositories

import com.cinematica.backend.domain.common.markers.Repository
import com.cinematica.backend.domain.users.types.User
import com.cinematica.backend.domain.users.types.value.EmailAddress
import com.cinematica.backend.domain.users.types.value.PasswordHash
import com.cinematica.backend.domain.users.types.value.UserId
import com.cinematica.backend.domain.users.types.value.UserName

interface UsersRepository : Repository {

    suspend fun createUser(
        emailAddress: EmailAddress,
        userName: UserName,
        userPassword: PasswordHash
    ): UserId

    suspend fun getUser(
        emailAddress: EmailAddress,
        userPassword: PasswordHash
    ): User?

    suspend fun isUserExist(emailAddress: EmailAddress): Boolean
}