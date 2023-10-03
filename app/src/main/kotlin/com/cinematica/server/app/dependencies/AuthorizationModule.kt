package com.cinematica.server.app.dependencies

import com.cinematica.backend.data.authorization.MysqlAuthorizationsRepository
import com.cinematica.backend.data.authorization.database.TableAuthorizationsDataSource
import com.cinematica.backend.data.authorization.database.mapper.DatabaseAuthorizationsMapper
import com.cinematica.backend.domain.authorization.repositories.AuthorizationsRepository
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val AuthorizationsModule = module {
    singleOf(::DatabaseAuthorizationsMapper)
    singleOf(::TableAuthorizationsDataSource)
    single<AuthorizationsRepository> {
        MysqlAuthorizationsRepository(
            authorizationsDataSource = get()
        )
    }
}