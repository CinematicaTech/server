enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven("https://jitpack.io")
    }
}

pluginManagement {
    repositories {
        gradlePluginPortal()
        mavenCentral()
        google()
    }
}

rootProject.name = "cinematica-server"

include(":app")

include(":domain")

include(":data")

include(":infrastructure:grpc-api")

include(
    ":foundation:cli-arguments",
    ":foundation:exposed-utils",
    ":foundation:validation",
    ":foundation:authorization",
    ":foundation:time",
    ":foundation:hashing",
)
