import com.android.build.api.dsl.LibraryExtension
import com.hamoda.convention.ExtensionType
import com.hamoda.convention.configureBuildTypes
import com.hamoda.convention.configureKotlinAndroid
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.kotlin

class AndroidLibraryConventionPluging : Plugin<Project> {
    override fun apply(target: Project) {
        target.run {
            pluginManager.apply {
                apply("com.android.library")
                apply("org.jetbrains.kotlin.android")
            }

            extensions.configure<LibraryExtension> {
                configureKotlinAndroid(commonExtension = this)

                configureBuildTypes(
                    commonExtension = this,
                    extensionType = ExtensionType.LIBRARYA
                )

                defaultConfig {
                    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
                    consumerProguardFiles("consumer-rules.pro")
                }

                dependencies {
                    "testImplementation"(kotlin("test"))
                }
            }
        }
    }
}