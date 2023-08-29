package com.cinematica.backend.app.dependencies

import com.cinematica.backend.data.authorization.datasource.PostgresqlAuthorizationsRepository
import com.cinematica.backend.data.authorization.db.TableAuthorizationDataSource
import com.cinematica.backend.data.authorization.db.mapper.DbAuthorizationMapper
import com.cinematica.backend.data.authorization.mapper.AuthorizationsMapper
import com.cinematica.backend.data.authorization.repository.AuthorizationsRepositoryImpl
import com.cinematica.backend.domain.authorization.datasource.AuthorizationsDataSourceRepository
import com.cinematica.backend.domain.authorization.repository.AuthorizationsRepository
import com.cinematica.backend.domain.authorization.usecases.signup.SignUpUseCase
import com.cinematica.backend.domain.authorization.usecases.signin.SignInUseCase
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val AuthorizationModule = module {
    singleOf(::DbAuthorizationMapper)
    single {
        TableAuthorizationDataSource(
            mapper = get(),
            database = get()
        )
    }
    single<AuthorizationsDataSourceRepository> {
        PostgresqlAuthorizationsRepository(
            tableAuthorizationsDataSource = get()
        )
    }
    single<AuthorizationsRepository> {
        AuthorizationsRepositoryImpl(
            authorizationsDataSourceRepository = get(),
            hashingService = get(),
            tokenService = get(),
            tokenConfig = get(),
            authorizationsMapper = get()
        )
    }
    singleOf(::AuthorizationsMapper)
    singleOf(::SignUpUseCase)
    singleOf(::SignInUseCase)
}