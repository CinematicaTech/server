package com.cinematica.backend.data.authorization.db

import com.cinematica.backend.data.authorization.db.mapper.DbAuthorizationMapper
import com.cinematica.backend.data.authorization.db.table.AuthorizationUsersTable
import com.cinematica.backend.domain.authorization.types.common.UserData
import org.ktorm.database.Database
import org.ktorm.dsl.eq
import org.ktorm.dsl.insert
import org.ktorm.entity.firstOrNull
import org.ktorm.entity.sequenceOf

class TableAuthorizationDataSource(
    private val mapper: DbAuthorizationMapper,
    private val database: Database
) {

    fun createAccount(userData: UserData) {
        database.insert(AuthorizationUsersTable) {
            set(it.email, userData.email)
            set(it.password, userData.password)
            set(it.salt, userData.salt)
        }
    }

    fun getUserByEmail(email: String): Result<UserData> {
        val data = database.sequenceOf(AuthorizationUsersTable).firstOrNull {
            AuthorizationUsersTable.email eq email
        }
        return if (data != null) {
            Result.success(mapper.toUserData(data))
        } else {
            Result.failure(Exception("user was not found"))
        }
    }
}