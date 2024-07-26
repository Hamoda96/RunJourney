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
    }
}