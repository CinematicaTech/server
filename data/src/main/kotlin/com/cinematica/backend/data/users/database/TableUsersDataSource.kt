package com.cinematica.backend.data.users.database

import com.cinematica.backend.data.users.database.mapper.DatabaseUsersMapper
import com.cinematica.backend.data.users.database.table.UsersTable
import com.cinematica.backend.foundation.exposed.suspendedTransaction
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction

class TableUsersDataSource(
    private val database: Database,
    private val mapper: DatabaseUsersMapper
) {
    init {
        transaction(database) {
            SchemaUtils.create(UsersTable)
        }
    }

    suspend fun isUserExist(email: String): Boolean = suspendedTransaction(database) {
        val user = UsersTable.select {
            UsersTable.USER_EMAIL eq email
        }.singleOrNull()
        user != null
    }
}