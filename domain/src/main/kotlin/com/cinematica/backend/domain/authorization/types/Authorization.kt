package com.cinematica.backend.domain.authorization.types

import com.cinematica.backend.domain.authorization.types.metadata.ClientMetadata
import com.cinematica.backend.domain.authorization.types.value.RefreshHash
import com.cinematica.backend.domain.authorization.types.value.AccessHash
import com.cinematica.backend.domain.users.types.value.UserId
import com.cinematica.backend.foundation.authorization.Scope
import com.cinematica.backend.foundation.time.UnixTime

data class Authorization(
    val userId: UserId,
    val accessHash: AccessHash,
    val refreshAccessHash: RefreshHash,
    val scopes: List<Scope>,
    val expiresAt: UnixTime,
    val createdAt: UnixTime,
    val clientMetadata: ClientMetadata,
)