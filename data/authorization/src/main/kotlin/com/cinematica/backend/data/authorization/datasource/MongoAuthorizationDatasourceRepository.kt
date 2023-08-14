package com.cinematica.backend.data.authorization.datasource

import com.cinematica.backend.data.authorization.entities.User
import com.cinematica.backend.data.authorization.mapper.AuthorizationDataMapper
import com.cinematica.backend.data.authorization.mapper.AuthorizationDatasourceMapper
import com.cinematica.backend.domain.authorization.datasource.AuthorizationDatasourceRepository
import com.cinematica.backend.domain.authorization.types.UserDomain
import com.cinematica.backend.domain.authorization.types.signup.SignUpData
import com.cinematica.backend.domain.authorization.types.state.AuthorizationState
import com.cinematica.backend.domain.authorization.types.state.AuthorizationStateData
import org.bson.conversions.Bson
import org.litote.kmongo.coroutine.CoroutineDatabase
import java.lang.Exception

class MongoAuthorizationDatasourceRepository(
    private val authorizationDataMapper: AuthorizationDataMapper,
    private val authorizationDatasourceMapper: AuthorizationDatasourceMapper,
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

    override suspend fun getUserByEmail(email: String): Result<UserDomain> {
        val user = usersCollection.findOne(filter = "{ email: '$email' }")
        return if (user == null) {
            Result.failure(Exception())
        } else {
            val userDomain = authorizationDatasourceMapper.mapToDomainUser(user)
            Result.success(userDomain)
        }
    }
}