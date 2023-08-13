plugins {
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.kotlinx.serialization)
}

dependencies {
    implementation(libs.kmongo.core)
    implementation(libs.kmongo.coroutines)

    implementation(projects.foundation.security)
    implementation(projects.domain.authorization)
}