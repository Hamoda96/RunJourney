import com.android.build.api.dsl.ApplicationExtension
import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties
import com.hamoda.convention.ExtensionType
import com.hamoda.convention.configureBuildTypes
import com.hamoda.convention.configureKotlinAndroid
import com.hamoda.convention.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

class AndroidApplicationConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        target.run {
            pluginManager.run {
                apply("com.android.application")
                apply("org.jetbrains.kotlin.android")
            }

            extensions.configure<ApplicationExtension> {
                defaultConfig {
                    applicationId = libs.findVersion("projectApplicationId").get().toString()
                    targetSdk = libs.findVersion("projectTargetSdkVersion").get().toString().toInt()
                    versionCode = libs.findVersion("projectVersionCode").get().toString().toInt()
                    versionName = libs.findVersion("projectVersionName").get().toString()

                    val MAPSAPIKEY = gradleLocalProperties(rootDir, rootProject.providers).getProperty("MAPS_API_KEY")
                    manifestPlaceholders["MAPS_API_KEY"] = MAPSAPIKEY
                }

                configureKotlinAndroid(commonExtension = this)

                configureBuildTypes(
                    commonExtension = this,
                    extensionType = ExtensionType.APPLICATION
                )
            }
        }
    }
}