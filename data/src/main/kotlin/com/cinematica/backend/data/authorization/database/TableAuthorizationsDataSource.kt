package com.cinematica.backend.data.authorization.database

import com.cinematica.backend.data.authorization.database.mapper.DatabaseAuthorizationsMapper
import com.cinematica.backend.data.authorization.database.table.AuthorizationsTable
import com.cinematica.backend.foundation.exposed.suspendedTransaction
import kotlinx.serialization.json.Json
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.insert
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

    suspend fun saveRefreshToken(
        refreshHash: String, userId: Long
    ): Unit = suspendedTransaction(database) {
        AuthorizationsTable.insert {
            it[USER_ID] = userId
            it[REFRESH_TOKEN] = refreshHash
        }.resultedValues
    }
}