package com.cinematica.backend.data.users

import com.cinematica.backend.data.users.database.TableUsersDataSource
import com.cinematica.backend.domain.users.repositories.UsersRepository
import com.cinematica.backend.domain.users.types.value.EmailAddress
import com.cinematica.backend.domain.users.types.value.PasswordHash
import com.cinematica.backend.domain.users.types.value.UserId
import com.cinematica.backend.domain.users.types.value.UserName
import com.cinematica.backend.domain.users.types.value.UserPassword
import com.cinematica.backend.foundation.time.UnixTime
import com.cinematica.backend.foundation.validation.createOrThrowInternally

class MysqlUsersRepository(
    private val tableUsersDataSource: TableUsersDataSource
) : UsersRepository {
    override suspend fun createUser(
        emailAddress: EmailAddress,
        userName: UserName,
        userPassword: PasswordHash
    ): UserId {
        return tableUsersDataSource.createUser(
            email = emailAddress.string,
            userName = userName.string,
            userPassword = userPassword.hash
        ).let { UserId.createOrThrowInternally(it) }
    }

    override suspend fun isUserExists(emailAddress: EmailAddress): Boolean {
        return tableUsersDataSource.isUserExist(emailAddress.string)
    }
}