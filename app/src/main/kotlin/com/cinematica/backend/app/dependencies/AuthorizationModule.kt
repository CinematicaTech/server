package com.cinematica.backend.app.dependencies

import com.cinematica.backend.data.authorization.datasource.MongoAuthorizationDatasourceRepository
import com.cinematica.backend.data.authorization.repository.AuthorizationRepositoryImpl
import com.cinematica.backend.domain.authorization.datasource.AuthorizationDatasourceRepository
import com.cinematica.backend.domain.authorization.mapper.AuthorizationDomainMapper
import com.cinematica.backend.data.authorization.mapper.AuthorizationDataMapper
import com.cinematica.backend.domain.authorization.repository.AuthorizationRepository
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import com.cinematica.backend.domain.authorization.usecases.signup.SignUpUseCase
import com.cinematica.backend.domain.authorization.usecases.state.AuthorizationStateUseCase

val AuthorizationModule = module {
    singleOf(::AuthorizationDomainMapper)
    singleOf(::AuthorizationDataMapper)
    single<AuthorizationDatasourceRepository> {
        MongoAuthorizationDatasourceRepository(
            authorizationDataMapper = get(),
            database = get()
        )
    }
    single<AuthorizationRepository> { AuthorizationRepositoryImpl(get()) }
    singleOf(::SignUpUseCase)
    singleOf(::AuthorizationStateUseCase)
}