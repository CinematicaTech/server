package com.cinematica.backend.data.authorization.datasource

import com.cinematica.backend.domain.authorization.repositories.AuthorizationDatasourceRepository
import org.litote.kmongo.coroutine.CoroutineDatabase

class MongoAuthorizationDatasourceRepository(
    private val database: CoroutineDatabase
): AuthorizationDatasourceRepository {



}