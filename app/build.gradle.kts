import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

plugins {
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.kotlinx.serialization)
    alias(libs.plugins.shadow.jar)
    application
}

dependencies {
    implementation(projects.infrastructure.grpcApi)
    implementation(projects.foundation.cliArguments)
    implementation(projects.foundation.exposedUtils)
    implementation(projects.domain)
    implementation(projects.data)

    implementation(libs.kotlinx.serialization)
    implementation(libs.kotlinx.coroutines)

    implementation(libs.mysql.connector)
    implementation(libs.exposed.core)
    implementation(libs.exposed.jdbc)

    implementation(libs.grpc.netty)
    implementation(libs.grpc.services)

    implementation(libs.koin.core)

    implementation(libs.logback.classic)
}

application {
    mainClass.set("com.cinematica.server.app.ApplicationKt")
}


tasks.withType<ShadowJar> {
    archiveBaseName.set("application")
    archiveClassifier.set("")
}
