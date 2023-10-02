package com.cinematica.backend.data.authorization.database.table

import org.jetbrains.exposed.sql.Table

object AuthorizationsTable : Table("authorizations") {
    val AUTHORIZATION_ID = integer("authorization_id").autoIncrement()
    val USER_ID = long("user_id").references(UsersTable.USER_ID)
//    val ACCESS_TOKEN = varchar("access_token", AccessHash.SIZE)
//    val REFRESH_TOKEN = varchar("refresh_token", AccessHash.SIZE)
    val EXPIRES_AT = long("access_token_expires_at")
    val CREATION_TIME = long("creation_time")

    override val primaryKey: PrimaryKey = PrimaryKey(USER_ID, AUTHORIZATION_ID)
}