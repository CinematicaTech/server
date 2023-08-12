package com.cinematica.backend.app.dependencies

import com.cinematica.backend.app.dependencies.configuration.DatabaseConfig
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import org.litote.kmongo.coroutine.CoroutineDatabase
import com.mongodb.reactivestreams.client.MongoDatabase
import org.litote.kmongo.reactivestreams.KMongo

val DatabaseModule = module {
    single<MongoDatabase> {
        KMongo.createClient(get<DatabaseConfig>().databaseUrl)
            .getDatabase("cinematica_database")
    }
    singleOf(::CoroutineDatabase)
}