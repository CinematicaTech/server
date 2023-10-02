plugins {
    alias(libs.plugins.kotlin.jvm)
}

dependencies {
    api(projects.foundation.validation)
    api(projects.foundation.authorization)

    implementation(libs.kotlinx.coroutines)

    testImplementation(libs.kotlin.test)
    testImplementation(libs.junit.jupiter)
    testImplementation(libs.mockk)
}

tasks.withType<Test> {
    useJUnitPlatform()
}