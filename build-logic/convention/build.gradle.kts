plugins {
    `kotlin-dsl`
}

group = "com.hamoda.runjourney.buildlogic"

dependencies {
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.android.tools.common)
    compileOnly(libs.kotlin.gradlePlugin)
    compileOnly(libs.ksp.gradlePlugin)
    compileOnly(libs.room.gradlePlugin)
}

gradlePlugin{
    plugins {
        register("androidApplication") {
            id = "runjourney.android.application"
            implementationClass = "AndroidApplicationConventionPlugin"
        }
        register("androidApplicationCompose") {
            id = "runjourney.android.application.compose"
            implementationClass = "AndroidApplicationComposeConventionPlugin"
        }
        register("androidLibrary") {
            id = "runjourney.android.library"
            implementationClass = "AndroidLibraryConventionPluging"
        }
        register("androidLibraryCompose") {
            id = "runjourney.android.library.compose"
            implementationClass = "AndroidLibraryComposeConventionPluging"
        }
    }
}