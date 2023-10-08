package com.cinematica.backend.data.authorization.database.mapper

import com.cinematica.backend.data.authorization.database.entities.DatabaseAuthorization
import com.cinematica.backend.data.authorization.database.table.AuthorizationsTable
import org.jetbrains.exposed.sql.ResultRow

class DatabaseAuthorizationsMapper {
    fun resultRowToDbAuthorization(row: ResultRow): DatabaseAuthorization {
        return DatabaseAuthorization(
            authorizationId = row[AuthorizationsTable.AUTHORIZATION_ID],
            userId = row[AuthorizationsTable.USER_ID],
            accessHash = row[AuthorizationsTable.ACCESS_TOKEN],
            refreshAccessHash = row[AuthorizationsTable.REFRESH_TOKEN],
            expiresAt = row[AuthorizationsTable.EXPIRES_AT],
            createdAt = row[AuthorizationsTable.CREATION_TIME],
            permissions = DatabaseAuthorization.Permissions(
                authorization = row[AuthorizationsTable.AUTHORIZATIONS_PERMISSION],
                users = row[AuthorizationsTable.USERS_PERMISSION],
            ),
            metaClientName = row[AuthorizationsTable.META_CLIENT_NAME],
            metaClientVersion = row[AuthorizationsTable.META_CLIENT_VERSION],
            metaClientIpAddress = row[AuthorizationsTable.META_CLIENT_IP_ADDRESS],
        )
    }

}