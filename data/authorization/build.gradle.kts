plugins {
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.kotlinx.serialization)
}

dependencies {
    implementation(libs.codec.core)

    implementation(libs.h2.database)

    implementation(libs.ktorm.core)
    implementation(libs.ktorm.support.mysql)

    implementation(projects.foundation.security)
    implementation(projects.domain.authorization)
    implementation(projects.foundation.exposedUtils)
}