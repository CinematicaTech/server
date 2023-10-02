package com.cinematica.backend.data.authorization.database

import com.cinematica.backend.data.authorization.database.table.AuthorizationsTable
import com.cinematica.backend.data.authorization.database.mapper.DbAuthorizationsMapper
import kotlinx.serialization.json.Json
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction

class TableAuthorizationsDataSource(
    private val database: Database,
    private val mapper: DbAuthorizationsMapper,
    private val json: Json = Json,
) {
    init {
        transaction(database) {
            SchemaUtils.create(AuthorizationsTable)
        }
    }


}