package com.cinematica.backend.domain.authorization.repositories

import com.cinematica.backend.domain.authorization.types.AuthorizationState
import com.cinematica.backend.domain.authorization.types.EmailAddress

interface AuthorizationsRepository {

    suspend fun getAuthorizationState(emailAddress: EmailAddress): AuthorizationState
}