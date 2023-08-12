package com.cinematica.backend.data.authorization.datasource

import com.cinematica.backend.data.authorization.entities.User
import com.cinematica.backend.data.authorization.mapper.AuthorizationDataMapper
import com.cinematica.backend.domain.authorization.datasource.AuthorizationDatasourceRepository
import com.cinematica.backend.domain.authorization.types.signup.SignUpData
import com.cinematica.backend.domain.authorization.types.state.AuthorizationState
import com.cinematica.backend.domain.authorization.types.state.AuthorizationStateData
import org.bson.conversions.Bson
import org.litote.kmongo.coroutine.CoroutineDatabase

class MongoAuthorizationDatasourceRepository(
    private val authorizationDataMapper: AuthorizationDataMapper,
    database: CoroutineDatabase
) : AuthorizationDatasourceRepository {

    private val usersCollection = database.getCollection<User>("users")
    override suspend fun insertUser(signUpData: SignUpData) {
        val user = authorizationDataMapper.mapToUser(signUpData)
        val result = usersCollection.insertOne(user)
        val data = usersCollection.find().first()
        println(data)
    }

    override suspend fun getState(authorizationStateData: AuthorizationStateData): AuthorizationState {
        val user = usersCollection.findOne(filter = "{ email: '${authorizationStateData.email}' }")
        return if (user == null) {
            AuthorizationState.SignUp
        } else {
            AuthorizationState.SignIn
        }
    }
}