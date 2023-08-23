package com.cinematica.backend.domain.authorization.datasource

interface AuthorizationsDataSourceRepository {

    suspend fun createAccount()
}