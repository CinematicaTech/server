package com.cinematica.backend.app.dependencies

import com.cinematica.backend.domain.authorization.old.datasource.AuthorizationDatasourceRepository
import com.cinematica.backend.domain.authorization.old.mapper.AuthorizationDomainMapper
import com.cinematica.backend.domain.authorization.old.repository.AuthorizationRepository
import com.cinematica.backend.domain.authorization.old.usecases.signup.SignUpUseCase
import com.cinematica.backend.domain.authorization.old.usecases.signin.SignInUseCase
import com.cinematica.backend.domain.authorization.old.usecases.state.AuthorizationStateUseCase
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