package com.cinematica.backend.data.authorization.db.mapper

import com.cinematica.backend.data.authorization.db.table.AuthorizationUsersEntity
import com.cinematica.backend.domain.authorization.types.common.UserData

class DbAuthorizationMapper {
    fun toUserData(authorizationUsersEntity: AuthorizationUsersEntity): UserData {
        return UserData(
            email = authorizationUsersEntity.email,
            password = authorizationUsersEntity.password,
            salt = authorizationUsersEntity.salt
        )
    }
}