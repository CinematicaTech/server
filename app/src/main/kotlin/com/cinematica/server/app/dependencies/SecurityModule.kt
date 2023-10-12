package com.cinematica.server.app.dependencies

import com.cinematica.backend.foundation.hashing.BCryptHashing
import com.cinematica.backend.foundation.hashing.HashingRepository
import com.cinematica.backend.foundation.hashing.Salt
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val SecurityModule = module {
    single<HashingRepository> {
        BCryptHashing(get())
    }
}