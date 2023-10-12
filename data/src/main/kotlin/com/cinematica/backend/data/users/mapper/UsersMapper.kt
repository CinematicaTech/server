package com.cinematica.backend.data.users.mapper

import com.cinematica.backend.data.common.markers.Mapper
import com.cinematica.backend.data.users.database.entities.DatabaseUser
import com.cinematica.backend.domain.users.types.User
import com.cinematica.backend.domain.users.types.value.EmailAddress
import com.cinematica.backend.domain.users.types.value.PasswordHash
import com.cinematica.backend.domain.users.types.value.UserId
import com.cinematica.backend.domain.users.types.value.UserName
import com.cinematica.backend.foundation.validation.createOrThrowInternally

class UsersMapper : Mapper {
    fun dbAuthToDomainAuth(user: DatabaseUser): User = with(user) {
        return User(
            userId = UserId.createOrThrowInternally(id),
            email = EmailAddress.createOrThrowInternally(email),
            password = PasswordHash.createOrThrowInternally(password),
            username = UserName.createOrThrowInternally(name)
        )
    }
}