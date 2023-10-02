package com.cinematica.backend.data.authorization

import com.cinematica.backend.domain.authorization.repositories.AuthorizationsRepository
import com.cinematica.backend.domain.authorization.types.AuthorizationState
import com.cinematica.backend.domain.authorization.types.value.EmailAddress

class MysqlAuthorizationsRepository : AuthorizationsRepository {
    override suspend fun getAuthorizationState(emailAddress: EmailAddress): AuthorizationState {
        TODO("Not yet implemented")
    }
}