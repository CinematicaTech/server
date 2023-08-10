plugins {
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.kotlinx.serialization)
}

dependencies {
    implementation(libs.kmongo.core)
    implementation(libs.kmongo.coroutines)

    implementation(projects.domain.authorization)
    implementation(projects.foundation.validation)
}