package com.cinematica.backend.domain.authorization.repositories

import com.cinematica.backend.domain.authorization.types.AuthorizationState
import com.cinematica.backend.domain.common.markers.Repository
import com.cinematica.backend.domain.users.types.value.EmailAddress

interface AuthorizationsRepository : Repository {

    suspend fun getAuthorizationState(emailAddress: EmailAddress): AuthorizationState
}