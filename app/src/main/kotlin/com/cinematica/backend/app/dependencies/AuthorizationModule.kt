package com.cinematica.backend.app.dependencies

import com.cinematica.backend.data.authorization.repository.AuthorizationRepositoryImpl
import com.cinematica.backend.domain.authorization.repositories.AuthorizationRepository
import com.cinematica.backend.domain.authorization.usecases.GetAuthorizationStateUseCase
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val AuthorizationModule = module {

    single<AuthorizationRepository> { AuthorizationRepositoryImpl() }

    singleOf(::GetAuthorizationStateUseCase)
}