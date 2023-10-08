package com.cinematica.backend.data.authorization

import com.cinematica.backend.data.authorization.database.TableAuthorizationsDataSource
import com.cinematica.backend.data.authorization.database.entities.DatabaseAuthorization
import com.cinematica.backend.data.authorization.mapper.AuthorizationsMapper
import com.cinematica.backend.domain.authorization.repositories.AuthorizationsRepository
import com.cinematica.backend.domain.authorization.types.Authorization
import com.cinematica.backend.domain.authorization.types.metadata.ClientMetadata
import com.cinematica.backend.domain.authorization.types.value.AccessHash
import com.cinematica.backend.domain.authorization.types.value.RefreshHash
import com.cinematica.backend.domain.users.types.value.UserId
import com.cinematica.backend.foundation.time.UnixTime

class MysqlAuthorizationsRepository(
    private val authorizationsDataSource: TableAuthorizationsDataSource,
    private val mapper: AuthorizationsMapper
) : AuthorizationsRepository {

    override suspend fun createAuthorization(
        userId: UserId,
        refreshHash: RefreshHash,
        accessToken: AccessHash,
        expiresAt: UnixTime,
        creationTime: UnixTime,
        clientMetadata: ClientMetadata,
    ) {
        authorizationsDataSource.createAuthorization(
            userId = userId.long,
            refreshHash = refreshHash.string,
            accessToken = accessToken.string,
            expiresAt = expiresAt.inMilliseconds,
            permissions = DatabaseAuthorization.Permissions.All,
            creationTime = creationTime.inMilliseconds,
            metaClientName = clientMetadata.clientName.string,
            metaClientVersion = clientMetadata.clientVersion.double,
            metaClientIpAddress = clientMetadata.clientIpAddress.string,
        )
    }

    override suspend fun get(accessToken: AccessHash, afterTime: UnixTime): Authorization? {
        return authorizationsDataSource.getAuthorization(accessToken.string, afterTime.inMilliseconds)
            ?.let(mapper::dbAuthToDomainAuth)
    }
}