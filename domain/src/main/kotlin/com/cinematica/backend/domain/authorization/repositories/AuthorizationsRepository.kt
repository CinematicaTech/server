package com.cinematica.backend.domain.authorization.repositories

import com.cinematica.backend.domain.authorization.types.metadata.ClientMetadata
import com.cinematica.backend.domain.authorization.types.value.AccessHash
import com.cinematica.backend.domain.authorization.types.value.RefreshHash
import com.cinematica.backend.domain.common.markers.Repository
import com.cinematica.backend.domain.users.types.value.UserId
import com.cinematica.backend.foundation.time.UnixTime

interface AuthorizationsRepository : Repository {

    suspend fun createAuthorization(
        userId: UserId,
        refreshHash: RefreshHash,
        accessHash: AccessHash,
        clientMetadata: ClientMetadata,
        expiresAt: UnixTime,
        creationTime: UnixTime
    )
}