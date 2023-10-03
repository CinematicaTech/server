package com.cinematica.backend.data.users

import com.cinematica.backend.domain.users.repositories.UsersRepository
import com.cinematica.backend.domain.users.types.value.EmailAddress

class MysqlUsersRepository : UsersRepository {
    override suspend fun createUser(emailAddress: EmailAddress) {
        TODO("Not yet implemented")
    }
}