package com.cinematica.backend.data.authorization

import com.cinematica.backend.data.authorization.database.TableAuthorizationsDataSource
import com.cinematica.backend.domain.authorization.repositories.AuthorizationsRepository
import com.cinematica.backend.domain.authorization.types.AuthorizationState
import com.cinematica.backend.domain.authorization.types.value.RefreshHash
import com.cinematica.backend.domain.users.types.value.EmailAddress
import com.cinematica.backend.domain.users.types.value.UserId

class MysqlAuthorizationsRepository(
    private val authorizationsDataSource: TableAuthorizationsDataSource
) : AuthorizationsRepository {

    override suspend fun saveRefreshToken(refreshHash: RefreshHash, userId: UserId) {
        authorizationsDataSource.saveRefreshToken(refreshHash.string, userId.long)
    }
}