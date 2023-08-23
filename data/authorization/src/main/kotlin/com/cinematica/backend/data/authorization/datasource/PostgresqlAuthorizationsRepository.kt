package com.cinematica.backend.data.authorization.datasource

import com.cinematica.backend.data.authorization.db.TableAuthorizationDataSource
import com.cinematica.backend.data.authorization.mapper.AuthorizationsMapper
import com.cinematica.backend.domain.authorization.datasource.AuthorizationsDataSourceRepository
import com.cinematica.backend.domain.authorization.repository.AuthorizationsRepository
import com.cinematica.backend.domain.authorization.types.authorization.AuthorizationRequest
import com.cinematica.backend.domain.authorization.types.authorization.AuthorizationResponse
import com.cinematica.backend.foundation.security.hashing.HashingService
import com.cinematica.backend.foundation.security.token.data.TokenConfig
import com.main.security.token.TokenService

class PostgresqlAuthorizationsRepository(
    private val tableAuthorizationsDataSource: TableAuthorizationDataSource,
) : AuthorizationsDataSourceRepository {


}