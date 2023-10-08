package com.cinematica.backend.data.authorization.database.entities

data class DatabaseAuthorization(
    val authorizationId: Int,
    val userId: Long,
    val accessHash: String,
    val refreshAccessHash: String,
    val permissions: Permissions,
    val expiresAt: Long,
    val createdAt: Long,
    val metaClientName: String,
    val metaClientVersion: Double,
    val metaClientIpAddress: String,
) {
    data class Permissions(
        val authorization: GrantLevel,
        val users: GrantLevel,
    ) {
        companion object {
            val All = Permissions(
                authorization = GrantLevel.WRITE,
                users = GrantLevel.WRITE,
            )
        }

        enum class GrantLevel {
            READ,
            WRITE,
            NOT_GRANTED,
        }
    }
}