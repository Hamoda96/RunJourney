import com.android.build.api.dsl.LibraryExtension
import com.hamoda.convention.configureAndroidCompose
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.getByType

class AndroidLibraryComposeConventionPluging : Plugin<Project> {
    override fun apply(target: Project) {
        target.run {
            pluginManager.apply {
                apply("runjourney.android.library")
            }

            val extension = extensions.getByType<LibraryExtension>()
            configureAndroidCompose(commonExtension = extension)
        }
    }
}