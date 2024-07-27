plugins {
    alias(libs.plugins.runjourney.android.library)
    alias(libs.plugins.runjourney.jvm.ktor)
}

android {
    namespace = "com.hamoda.core.data"
}

dependencies {
    implementation(libs.timber)
    implementation(libs.bundles.koin)

    implementation(projects.core.domain)
    implementation(projects.core.database)
}