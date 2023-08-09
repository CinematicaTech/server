import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

plugins {
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.shadow.jar)
    application
}

dependencies {
    implementation(libs.slf4j.nop)

    implementation(libs.ktor.server.core)
    implementation(libs.ktor.server.netty)

    implementation(projects.foundation.cliArguments)
}

application {
    mainClass.set("com.cinematica.backend.app.ApplicationKt")
}

tasks.withType<ShadowJar> {
    archiveBaseName.set("application")
    archiveClassifier.set("")
}
