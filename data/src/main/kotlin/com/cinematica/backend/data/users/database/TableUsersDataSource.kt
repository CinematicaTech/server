package com.cinematica.backend.data.users.database

import com.cinematica.backend.data.users.database.mapper.DatabaseUsersMapper
import com.cinematica.backend.data.users.database.table.UsersTable
import com.cinematica.backend.foundation.exposed.suspendedTransaction
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.insert
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

    suspend fun createUser(
        email: String,
        userName: String,
        userPassword: String
    ): Long = suspendedTransaction(database) {
        UsersTable.insert {
            it[USER_EMAIL] = email
            it[USER_NAME] = userName
            it[USER_PASSWORD] = userPassword
        }.resultedValues!!.single().let(mapper::resultRowToDatabaseUser).id
    }

    suspend fun isUserExistByEmail(email: String): Boolean = suspendedTransaction(database) {
        val user = UsersTable.select {
            UsersTable.USER_EMAIL eq email
        }.singleOrNull()
        user != null
    }

    suspend fun isUserExistByUserName(username: String): Boolean = suspendedTransaction(database) {
        val user = UsersTable.select {
            UsersTable.USER_NAME eq username
        }.singleOrNull()
        user != null
    }
}