package com.cinematica.backend.data.users.database.table

import org.jetbrains.exposed.sql.Table

object UsersTable : Table("users") {
    val USER_ID = long("user_id").autoIncrement()
    val USER_EMAIL = varchar("user_email", 200)
    val USER_NICKNAME = varchar("user_nickname", 50)
    val USER_FIRST_NAME = varchar("user_first_name", 50)
    val USER_LAST_NAME = varchar("user_last_name", 50)
    val CREATION_TIME = long("creation_time")

    override val primaryKey: PrimaryKey = PrimaryKey(USER_ID)
}