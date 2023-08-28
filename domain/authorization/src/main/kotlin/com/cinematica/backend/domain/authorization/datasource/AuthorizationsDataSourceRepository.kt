package com.cinematica.backend.domain.authorization.datasource

import com.cinematica.backend.domain.authorization.types.common.UserData

interface AuthorizationsDataSourceRepository {

    suspend fun createAccount(userData: UserData)
}