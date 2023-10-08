package com.cinematica.backend.domain.authorization.types

import com.cinematica.backend.domain.authorization.types.value.RefreshHash
import com.cinematica.backend.domain.authorization.types.value.AccessHash
import com.cinematica.backend.domain.users.types.value.UserId
import com.cinematica.backend.foundation.authorization.Scope
import com.cinematica.backend.foundation.time.UnixTime

data class Authorization(
    val userId: UserId,
    val refreshAccessHash: RefreshHash,
    val accessHash: AccessHash,
    val scopes: List<Scope>,
    val expiresAt: UnixTime,
    val creationTime: UnixTime,
)