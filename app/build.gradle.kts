plugins {
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.shadow.jar)
}

dependencies {
    implementation(libs.slf4j.nop)

    implementation(libs.ktor.server.core)
    implementation(libs.ktor.server.netty)
    implementation(libs.ktor.server.call.logging)
    implementation(libs.ktor.serialization.kotlinx.json)
    implementation(libs.ktor.server.content.negotiation)

    implementation(libs.koin.core)
    implementation(libs.koin.anotations)

    implementation(libs.kmongo.core)
    implementation(libs.kmongo.coroutines)

    implementation(projects.domain.authorization)
    implementation(projects.data.authorization)
    implementation(projects.foundation.cliArguments)
    implementation(projects.foundation.security)
}