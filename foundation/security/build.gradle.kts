plugins {
    alias(libs.plugins.kotlin.jvm)
}

dependencies {
    implementation(libs.codec.core)
    implementation(libs.jwt.core)
}