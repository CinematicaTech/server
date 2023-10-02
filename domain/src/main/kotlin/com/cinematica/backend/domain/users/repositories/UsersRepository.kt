package com.cinematica.backend.domain.users.repositories

import com.cinematica.backend.domain.common.markers.Repository
import com.cinematica.backend.domain.users.types.value.EmailAddress

interface UsersRepository : Repository {

    suspend fun createUser(
        emailAddress: EmailAddress,

    )
}