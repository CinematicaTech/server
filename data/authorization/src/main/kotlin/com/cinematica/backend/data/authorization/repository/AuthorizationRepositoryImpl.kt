package com.cinematica.backend.data.authorization.repository

import com.cinematica.backend.data.authorization.datasource.MongoAuthorizationDatasourceRepository
import com.cinematica.backend.data.authorization.mappers.AuthorizationValueMapper
import com.cinematica.backend.domain.authorization.repositories.AuthorizationRepository
import com.cinematica.backend.domain.authorization.types.signup.SignUpRequest

class AuthorizationRepositoryImpl(
    private val authorizationValueMapper: AuthorizationValueMapper,
    private val mongoAuthorizationDatasourceRepository: MongoAuthorizationDatasourceRepository
) : AuthorizationRepository {

    override fun signUp(signUpRequest: SignUpRequest) {
        val signUpRequestValue = authorizationValueMapper.toSignUpRequestValue(signUpRequest)
    }
}