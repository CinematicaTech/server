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

include(
    ":foundation:cli-arguments",
    ":foundation:security",
    ":foundation:exposed-utils",
)

include(
    ":domain:authorization",
)

include(
    ":data:authorization",
)
