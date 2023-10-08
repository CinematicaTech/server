package com.cinematica.backend.data.authorization.mapper

import com.cinematica.backend.data.authorization.database.entities.DatabaseAuthorization
import com.cinematica.backend.data.authorization.database.entities.DatabaseAuthorization.Permissions.*
import com.cinematica.backend.data.common.markers.Mapper
import com.cinematica.backend.domain.authorization.types.Authorization
import com.cinematica.backend.domain.authorization.types.AuthorizationsScope
import com.cinematica.backend.domain.authorization.types.metadata.ClientMetadata
import com.cinematica.backend.domain.authorization.types.metadata.value.ClientIpAddress
import com.cinematica.backend.domain.authorization.types.metadata.value.ClientName
import com.cinematica.backend.domain.authorization.types.metadata.value.ClientVersion
import com.cinematica.backend.domain.authorization.types.value.AccessHash
import com.cinematica.backend.domain.authorization.types.value.RefreshHash
import com.cinematica.backend.domain.users.types.UsersScope
import com.cinematica.backend.domain.users.types.value.UserId
import com.cinematica.backend.foundation.authorization.Scope
import com.cinematica.backend.foundation.time.UnixTime
import com.cinematica.backend.foundation.validation.createOrThrowInternally

class AuthorizationsMapper : Mapper {
    fun dbAuthToDomainAuth(auth: DatabaseAuthorization): Authorization = with(auth) {
        return Authorization(
            userId = UserId.createOrThrowInternally(auth.userId),
            accessHash = AccessHash.createOrThrowInternally(auth.accessHash),
            refreshAccessHash = RefreshHash.createOrThrowInternally(auth.refreshAccessHash),
            scopes = dbPermissionsToDomain(permissions),
            expiresAt = UnixTime.createOrThrowInternally(expiresAt),
            createdAt = UnixTime.createOrThrowInternally(createdAt),
            clientMetadata = ClientMetadata(
                clientName = ClientName.createOrThrowInternally(auth.metaClientName),
                clientVersion = ClientVersion.createOrThrowInternally(auth.metaClientVersion),
                clientIpAddress = ClientIpAddress.createOrThrowInternally(auth.metaClientIpAddress),
            )
        )
    }

    private fun dbPermissionsToDomain(
        dbPermissions: DatabaseAuthorization.Permissions,
    ): List<Scope> = with(dbPermissions) {
        return buildList {
            if (authorization != DatabaseAuthorization.Permissions.GrantLevel.NOT_GRANTED) {
                add(
                    if (authorization == GrantLevel.WRITE) AuthorizationsScope.Write
                    else AuthorizationsScope.Read
                )
            }

            if (users != GrantLevel.NOT_GRANTED) {
                add(
                    if (users == GrantLevel.WRITE) UsersScope.Write
                    else UsersScope.Read
                )
            }
        }
    }
}