package com.cinematica.server.app.dependencies

import com.cinematica.backend.infrastructure.grpc.authorization.AuthorizationsService
import com.cinematica.backend.infrastructure.grpc.users.UsersService
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val GrpcServicesModule = module {
    singleOf(::UsersService)
    singleOf(::AuthorizationsService)
}