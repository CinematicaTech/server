plugins {
    alias(libs.plugins.kotlinx.serialization)
    alias(libs.plugins.kotlin.jvm)
}

dependencies {
    implementation(libs.ktor.serialization.kotlinx.json)
}