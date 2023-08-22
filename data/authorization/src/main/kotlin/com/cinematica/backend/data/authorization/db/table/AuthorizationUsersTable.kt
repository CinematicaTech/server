package com.cinematica.backend.data.authorization.db.table

import org.jetbrains.exposed.sql.Table

object AuthorizationUsersTable : Table("authorization_users_table") {
    val USER_ID = long("user_id").autoIncrement()
    val EMAIL = varchar("email", 100)
    val PASSWORD = varchar("password", 128)
    val SALT = varchar("salt", 128)
}