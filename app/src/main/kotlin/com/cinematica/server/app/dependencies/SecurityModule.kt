package com.cinematica.server.app.dependencies

import com.cinematica.backend.foundation.hashing.BCryptHashing
import com.cinematica.backend.foundation.hashing.HashingRepository
import org.koin.dsl.module

val SecurityModule = module {
    single<HashingRepository> {
        BCryptHashing()
    }
}