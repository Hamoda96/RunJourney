plugins {
    alias(libs.plugins.runjourney.android.library)
    alias(libs.plugins.runjourney.jvm.ktor)
}

android {
    namespace = "com.runjourney.run.network"
}

dependencies {
    implementation(projects.core.domain)
    implementation(projects.core.data)
}