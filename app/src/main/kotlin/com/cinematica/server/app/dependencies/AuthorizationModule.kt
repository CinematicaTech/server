package com.cinematica.server.app.dependencies

import com.cinematica.backend.data.authorization.MysqlAuthorizationsRepository
import com.cinematica.backend.data.authorization.database.TableAuthorizationsDataSource
import com.cinematica.backend.data.authorization.database.mapper.DatabaseAuthorizationsMapper
import com.cinematica.backend.data.authorization.mapper.AuthorizationsMapper
import com.cinematica.backend.domain.authorization.repositories.AuthorizationsRepository
import com.cinematica.backend.domain.authorization.usecases.SignUpUseCase
import com.cinematica.backend.domain.authorization.usecases.GetAuthorizationStateUseCase
import com.cinematica.backend.domain.authorization.usecases.GetAuthorizationUseCase
import com.cinematica.backend.domain.authorization.usecases.SignInUseCase
import com.cinematica.backend.infrastructure.grpc.authorization.mapper.GrpcAuthorizationsMapper
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val AuthorizationsModule = module {
    singleOf(::DatabaseAuthorizationsMapper)
    singleOf(::TableAuthorizationsDataSource)
    singleOf(::SignUpUseCase)
    singleOf(::SignInUseCase)
    singleOf(::GetAuthorizationUseCase)
    singleOf(::GrpcAuthorizationsMapper)
    singleOf(::GetAuthorizationStateUseCase)
    singleOf(::AuthorizationsMapper)

    single<AuthorizationsRepository> {
        MysqlAuthorizationsRepository(
            authorizationsDataSource = get(),
            mapper = get(),
        )
    }
}