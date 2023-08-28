package com.cinematica.backend.data.authorization.mapper

import com.cinematica.backend.domain.authorization.types.authorization.AuthorizationRequest
import com.cinematica.backend.domain.authorization.types.common.UserData
import com.cinematica.backend.foundation.security.hashing.data.SaltedHash

class AuthorizationsMapper {

    fun toUserData(authorizationRequest: AuthorizationRequest, salt: SaltedHash): UserData {
        return UserData(
            email = authorizationRequest.email,
            password = salt.hash,
            salt = salt.salt
        )
    }
}