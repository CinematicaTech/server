import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

plugins {
    alias(libs.plugins.kotlinx.serialization)
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.shadow.jar)
    application
}

dependencies {
    implementation(libs.ktor.server.core)
    implementation(libs.ktor.server.netty)
    implementation(libs.ktor.server.call.logging)
    implementation(libs.ktor.serialization.kotlinx.json)
    implementation(libs.ktor.server.content.negotiation)

    implementation(libs.logback.classic)

    implementation(libs.koin.core)
    implementation(libs.koin.anotations)

    implementation(libs.ktorm.core)
    implementation(libs.ktorm.support.mysql)

    implementation(libs.mysql.connector)
    implementation(libs.h2.database)

    implementation(projects.domain.authorization)
    implementation(projects.data.authorization)
    implementation(projects.foundation.cliArguments)
    implementation(projects.foundation.security)
    implementation(projects.foundation.exposedUtils)
}

application {
    mainClass.set("com.cinematica.backend.app.ApplicationKt")
}

tasks.withType<ShadowJar> {
    archiveBaseName.set("application")
    archiveClassifier.set("")
}
