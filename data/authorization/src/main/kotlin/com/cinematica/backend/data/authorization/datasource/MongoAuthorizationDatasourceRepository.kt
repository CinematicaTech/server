package com.cinematica.backend.data.authorization.datasource

import com.cinematica.backend.data.authorization.entities.User
import com.cinematica.backend.data.authorization.mapper.AuthorizationDataMapper
import com.cinematica.backend.domain.authorization.datasource.AuthorizationDatasourceRepository
import com.cinematica.backend.domain.authorization.types.signup.SignUpData
import org.litote.kmongo.coroutine.CoroutineDatabase

class MongoAuthorizationDatasourceRepository(
    private val authorizationDataMapper: AuthorizationDataMapper,
    database: CoroutineDatabase
) : AuthorizationDatasourceRepository {

    private val usersCollection = database.getCollection<User>("users")
    override suspend fun insertUser(signUpData: SignUpData) {
        val user = authorizationDataMapper.mapToUser(signUpData)
        usersCollection.insertOne(user)
    }
}