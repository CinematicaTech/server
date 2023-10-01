package com.cinematica.server.app.dependencies

import com.cinematica.server.app.dependencies.configuration.DatabaseConfig
import org.jetbrains.exposed.sql.Database
import org.koin.dsl.module

val DatabaseModule = module {
    single {
        val config = get<DatabaseConfig>()

        Database.connect(
            url = config.url,
            user = config.user,
            password = config.password ?: "",
            driver = "com.mysql.cj.jdbc.Driver",
        )
    }
}