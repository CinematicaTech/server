package com.cinematica.server.app.dependencies

import kotlinx.serialization.json.Json
import org.koin.dsl.module

val CommonModule = module {
    single<Json> {
        Json {
            encodeDefaults = false
            ignoreUnknownKeys = true
            prettyPrint = false
        }
    }
}