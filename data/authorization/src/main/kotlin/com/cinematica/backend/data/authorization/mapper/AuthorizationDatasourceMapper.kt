package com.cinematica.backend.data.authorization.mapper

import com.cinematica.backend.data.authorization.entities.User
import com.cinematica.backend.domain.authorization.types.UserDomain

class AuthorizationDatasourceMapper {

    fun mapToDomainUser(user: User): UserDomain {
        return UserDomain(
            email = user.email,
            password = user.password,
            salt = user.salt,
            id = user.id
        )
    }
}