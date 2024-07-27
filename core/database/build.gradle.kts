plugins {
    alias(libs.plugins.runjourney.android.library)
    alias(libs.plugins.runjourney.android.room)
}

android {
    namespace = "com.hamoda.core.database"
}

dependencies {
    implementation(libs.org.mongodb.bson)
    implementation(libs.bundles.koin)

    implementation(projects.core.domain)
}