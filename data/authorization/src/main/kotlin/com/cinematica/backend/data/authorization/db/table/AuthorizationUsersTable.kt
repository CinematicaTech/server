package com.cinematica.backend.data.authorization.db.table

import org.ktorm.entity.Entity
import org.ktorm.schema.Table
import org.ktorm.schema.int
import org.ktorm.schema.varchar

object AuthorizationUsersTable: Table<AuthorizationUsersEntity>("authorization_users_table") {

    val id = int("id").primaryKey().bindTo { it.id }
    val email = varchar("email").bindTo { it.email }
    val password = varchar("password").bindTo { it.password }
    val salt = varchar("salt").bindTo { it.salt }
}

interface AuthorizationUsersEntity: Entity<AuthorizationUsersEntity> {

    companion object : Entity.Factory<AuthorizationUsersEntity>()

    val id: Int
    val email: String
    val password: String
    val salt: String
}