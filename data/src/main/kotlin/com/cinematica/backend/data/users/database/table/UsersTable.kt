package com.cinematica.backend.data.users.database.table

import org.jetbrains.exposed.sql.Table

object UsersTable : Table("users_table") {
    val USER_ID = long("user_id").autoIncrement()
    val USER_EMAIL = varchar("user_email", 200)
    val USER_NAME = varchar("user_name", 50)
    val USER_PASSWORD = varchar("user_password", 128)

    override val primaryKey: PrimaryKey = PrimaryKey(USER_ID)
}