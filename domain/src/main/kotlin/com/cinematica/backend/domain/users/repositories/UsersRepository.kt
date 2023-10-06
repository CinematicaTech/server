package com.cinematica.backend.domain.users.repositories

import com.cinematica.backend.domain.common.markers.Repository
import com.cinematica.backend.domain.users.types.value.EmailAddress
import com.cinematica.backend.domain.users.types.value.PasswordHash
import com.cinematica.backend.domain.users.types.value.UserId
import com.cinematica.backend.domain.users.types.value.UserName
import com.cinematica.backend.domain.users.types.value.UserPassword
import com.cinematica.backend.foundation.time.UnixTime

interface UsersRepository : Repository {

    suspend fun createUser(
        emailAddress: EmailAddress,
        userName: UserName,
        userPassword: PasswordHash
    ): UserId

    suspend fun isUserExists(emailAddress: EmailAddress): Boolean
}