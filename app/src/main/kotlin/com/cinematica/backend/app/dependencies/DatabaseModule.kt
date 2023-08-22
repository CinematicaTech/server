package com.cinematica.backend.app.dependencies

import com.cinematica.backend.app.dependencies.configuration.DatabaseConfig
import org.jetbrains.exposed.sql.Database
import org.koin.dsl.module

val DatabaseModule = module {
    single {
        val config = get<DatabaseConfig>()

        Database.connect(
            url = config.url,
            driver = "org.postgresql.Driver"
        )
    }
}