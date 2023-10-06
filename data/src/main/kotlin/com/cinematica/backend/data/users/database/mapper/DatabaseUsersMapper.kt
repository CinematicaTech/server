package com.cinematica.backend.data.users.database.mapper

import com.cinematica.backend.data.users.database.entities.DatabaseUser
import com.cinematica.backend.data.users.database.table.UsersTable
import org.jetbrains.exposed.sql.ResultRow

class DatabaseUsersMapper {
    fun resultRowToDatabaseUser(row: ResultRow): DatabaseUser {
        return DatabaseUser(
            id = row[UsersTable.USER_ID],
            email = row[UsersTable.USER_EMAIL],
            name = row[UsersTable.USER_NAME],
            password = row[UsersTable.USER_PASSWORD]
        )
    }
}