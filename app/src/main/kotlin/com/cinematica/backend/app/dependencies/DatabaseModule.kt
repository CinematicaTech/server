package com.cinematica.backend.app.dependencies

import com.cinematica.backend.app.dependencies.configuration.DatabaseConfig
import org.koin.dsl.module
import org.ktorm.database.Database

val DatabaseModule = module {
    single {
        val config = get<DatabaseConfig>()

        Database.connect(
            url = config.url,
            user = config.user,
            password = config.password ?: "",
            driver = "com.mysql.cj.jdbc.Driver"
        )
    }
}