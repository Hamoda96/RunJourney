plugins {
    alias(libs.plugins.runjourney.android.feature.ui)
}

android {
    namespace = "com.hamoda.auth.presentation"
}

dependencies {
    implementation(projects.core.domain)
    implementation(projects.auth.domain)
}