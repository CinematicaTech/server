package com.cinematica.server.app.dependencies

import com.cinematica.backend.foundation.random.RandomProvider
import com.cinematica.backend.foundation.random.SecureRandomProvider
import com.cinematica.backend.foundation.time.SystemTimeProvider
import com.cinematica.backend.foundation.time.TimeProvider
import kotlinx.serialization.json.Json
import org.koin.dsl.module
import java.time.ZoneId

val CommonModule = module {
    single<TimeProvider> {
        SystemTimeProvider(ZoneId.of("UTC"))
    }
    single<RandomProvider> {
        SecureRandomProvider()
    }
    single<Json> {
        Json {
            encodeDefaults = false
            ignoreUnknownKeys = true
            prettyPrint = false
        }
    }
}