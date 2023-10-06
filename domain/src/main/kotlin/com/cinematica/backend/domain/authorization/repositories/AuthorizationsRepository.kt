package com.cinematica.backend.domain.authorization.repositories

import com.cinematica.backend.domain.authorization.types.value.RefreshHash
import com.cinematica.backend.domain.common.markers.Repository
import com.cinematica.backend.domain.users.types.value.EmailAddress
import com.cinematica.backend.domain.users.types.value.UserId

interface AuthorizationsRepository : Repository {

    suspend fun saveRefreshToken(refreshHash: RefreshHash, userId: UserId)
}