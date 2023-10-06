package com.cinematica.backend.data.authorization.database.table

import com.cinematica.backend.data.users.database.table.UsersTable
import com.cinematica.backend.domain.authorization.types.value.RefreshHash
import org.jetbrains.exposed.sql.Table

object AuthorizationsTable : Table("authorizations_table") {
    val AUTHORIZATION_ID = integer("authorization_id").autoIncrement()
    val USER_ID = long("user_id").references(UsersTable.USER_ID)
    val REFRESH_TOKEN = varchar("refresh_token", RefreshHash.SIZE)

    override val primaryKey: PrimaryKey = PrimaryKey(USER_ID, AUTHORIZATION_ID, REFRESH_TOKEN)
}