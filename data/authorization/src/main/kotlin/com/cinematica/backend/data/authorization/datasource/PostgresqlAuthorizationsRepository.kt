package com.cinematica.backend.data.authorization.datasource

import com.cinematica.backend.data.authorization.db.TableAuthorizationDataSource
import com.cinematica.backend.domain.authorization.datasource.AuthorizationsDataSourceRepository
import com.cinematica.backend.domain.authorization.types.common.UserData

class PostgresqlAuthorizationsRepository(
    private val tableAuthorizationsDataSource: TableAuthorizationDataSource,
) : AuthorizationsDataSourceRepository {

    override suspend fun createAccount(userData: UserData) {
        tableAuthorizationsDataSource.createAccount(userData)
    }
}