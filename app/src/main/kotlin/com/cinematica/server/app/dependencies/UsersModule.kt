package com.cinematica.server.app.dependencies

import com.cinematica.backend.data.users.MysqlUsersRepository
import com.cinematica.backend.data.users.database.TableUsersDataSource
import com.cinematica.backend.data.users.database.mapper.DatabaseUsersMapper
import com.cinematica.backend.domain.users.repositories.UsersRepository
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val UsersModule = module {
    singleOf(::DatabaseUsersMapper)
    singleOf(::TableUsersDataSource)
    single<UsersRepository> {
        MysqlUsersRepository(
            tableUsersDataSource = get()
        )
    }
}