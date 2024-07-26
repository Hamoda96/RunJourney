plugins {
    alias(libs.plugins.runjourney.android.library)
}

android {
    namespace = "com.hamoda.auth.data"
}

dependencies {
    implementation(libs.bundles.koin)

    implementation(projects.auth.domain)
    implementation(projects.core.domain)
    implementation(projects.core.data)
}