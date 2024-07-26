plugins {
    alias(libs.plugins.runjourney.android.library)
}

android {
    namespace = "com.hamoda.core.data"
}

dependencies {
    implementation(libs.timber)

    implementation(projects.core.domain)
}