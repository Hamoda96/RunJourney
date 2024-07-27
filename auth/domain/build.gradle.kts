plugins {
    alias(libs.plugins.runjourney.jvm.library)
}

dependencies {
    implementation(projects.core.domain)
}