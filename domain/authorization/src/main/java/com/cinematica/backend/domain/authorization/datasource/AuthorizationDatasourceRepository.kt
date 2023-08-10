package com.cinematica.backend.domain.authorization.datasource

import com.cinematica.backend.domain.authorization.types.signup.SignUpData

interface AuthorizationDatasourceRepository {

    suspend fun insertUser(signUpData: SignUpData)
}