package com.cinematica.backend.app.dependencies

import com.cinematica.backend.data.authorization.datasource.MongoAuthorizationDatasourceRepository
import com.cinematica.backend.data.authorization.repository.AuthorizationRepositoryImpl
import com.cinematica.backend.domain.authorization.datasource.AuthorizationDatasourceRepository
import com.cinematica.backend.domain.authorization.mapper.AuthorizationDomainMapper
import com.cinematica.backend.data.authorization.mapper.AuthorizationDataMapper
import com.cinematica.backend.data.authorization.mapper.AuthorizationDatasourceMapper
import com.cinematica.backend.domain.authorization.repository.AuthorizationRepository
import com.cinematica.backend.domain.authorization.usecases.signup.SignUpUseCase
import com.cinematica.backend.domain.authorization.usecases.signin.SignInUseCase
import com.cinematica.backend.domain.authorization.usecases.state.AuthorizationStateUseCase
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val AuthorizationModule = module {
    singleOf(::AuthorizationDomainMapper)
    singleOf(::AuthorizationDataMapper)
    singleOf(::AuthorizationDatasourceMapper)
    single<AuthorizationDatasourceRepository> {
        MongoAuthorizationDatasourceRepository(
            authorizationDataMapper = get(),
            authorizationDatasourceMapper = get(),
            database = get(),
        )
    }
    single<AuthorizationRepository> { AuthorizationRepositoryImpl(
        authorizationDatasourceRepository = get(),
        hashingService = get(),
        tokenService = get(),
        authorizationDomainMapper = get(),
        tokenConfig = get(),
    ) }
    singleOf(::SignUpUseCase)
    singleOf(::SignInUseCase)
    singleOf(::AuthorizationStateUseCase)
}