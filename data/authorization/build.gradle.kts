plugins {
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.kotlinx.serialization)
}

dependencies {
    //todo remove kmongo
    implementation(libs.kmongo.core)
    implementation(libs.kmongo.coroutines)
    implementation(libs.codec.core)

    implementation(libs.exposed.core)

    implementation(projects.foundation.security)
    implementation(projects.domain.authorization)
}