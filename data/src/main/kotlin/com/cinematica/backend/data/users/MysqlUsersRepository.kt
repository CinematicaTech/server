package com.cinematica.backend.data.users

import com.cinematica.backend.data.users.database.TableUsersDataSource
import com.cinematica.backend.domain.users.repositories.UsersRepository
import com.cinematica.backend.domain.users.types.value.EmailAddress

class MysqlUsersRepository(
    private val tableUsersDataSource: TableUsersDataSource
) : UsersRepository {
    override suspend fun createUser(emailAddress: EmailAddress) {
    }

    override suspend fun isUserExists(emailAddress: EmailAddress): Boolean {
        return tableUsersDataSource.isUserExist(emailAddress.string)
    }
}