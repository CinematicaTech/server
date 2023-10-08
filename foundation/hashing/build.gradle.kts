plugins {
    alias(libs.plugins.kotlin.jvm)
}

dependencies {
    implementation(libs.jbcrypt.core)
    implementation("io.jsonwebtoken:jjwt-api:0.11.2")
    implementation("io.jsonwebtoken:jjwt-impl:0.11.2")
    implementation("io.jsonwebtoken:jjwt-jackson:0.11.2")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3")
}