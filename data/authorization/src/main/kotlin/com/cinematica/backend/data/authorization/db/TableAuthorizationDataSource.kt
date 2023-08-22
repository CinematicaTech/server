package com.cinematica.backend.data.authorization.db

import com.cinematica.backend.data.authorization.db.table.AuthorizationUsersTable
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction

class TableAuthorizationDataSource(
    private val database: Database,
) {

    init {
        transaction(database) {
            SchemaUtils.create(AuthorizationUsersTable)
        }
    }

}