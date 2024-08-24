pluginManagement {
    includeBuild("build-logic")
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "RunJourney"

gradle.startParameter.excludedTaskNames.addAll(listOf(":build-logic:convention:testClasses"))

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
include(":app")
include(":auth:data")
include(":auth:domain")
include(":auth:presentation")
include(":run:data")
include(":run:presentation")
include(":run:domain")
include(":core:data")
include(":core:domain")
include(":core:presentation:ui")
include(":core:presentation:designsystem")
include(":core:notification")
include(":core:database")
include(":core:connectivity:data")
include(":core:connectivity:domain")
