package com.cinematica.backend.data.authorization.database

import com.cinematica.backend.data.authorization.database.entities.DatabaseAuthorization
import com.cinematica.backend.data.authorization.database.mapper.DatabaseAuthorizationsMapper
import com.cinematica.backend.data.authorization.database.table.AuthorizationsTable
import com.cinematica.backend.foundation.exposed.suspendedTransaction
import kotlinx.serialization.json.Json
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction

class TableAuthorizationsDataSource(
    private val database: Database,
    private val mapper: DatabaseAuthorizationsMapper,
    private val json: Json = Json,
) {
    init {
        transaction(database) {
            SchemaUtils.create(AuthorizationsTable)
        }
    }

    suspend fun createAccount(
        userId: Long,
        refreshHash: String,
        accessToken: String,
        expiresAt: Long,
        creationTime: Long,
        permissions: DatabaseAuthorization.Permissions,
        metaClientName: String,
        metaClientVersion: Double,
        metaClientIpAddress: String,
    ): Unit = suspendedTransaction(database) {
        AuthorizationsTable.insert {
            it[USER_ID] = userId
            it[REFRESH_TOKEN] = refreshHash
            it[ACCESS_TOKEN] = accessToken
            it[EXPIRES_AT] = expiresAt
            it[CREATION_TIME] = creationTime
            it[AUTHORIZATIONS_PERMISSION] = permissions.authorization
            it[USERS_PERMISSION] = permissions.users
            it[META_CLIENT_NAME] = metaClientName
            it[META_CLIENT_VERSION] = metaClientVersion
            it[META_CLIENT_IP_ADDRESS] = metaClientIpAddress
        }.resultedValues
    }

    suspend fun getAuthorization(
        accessHash: String,
        currentTime: Long,
    ): DatabaseAuthorization? = suspendedTransaction(database) {
        AuthorizationsTable.select {
            AuthorizationsTable.ACCESS_TOKEN eq accessHash
            //and
//                    (AuthorizationsTable.EXPIRES_AT less currentTime)
        }.singleOrNull()?.let(mapper::resultRowToDbAuthorization)
    }
}