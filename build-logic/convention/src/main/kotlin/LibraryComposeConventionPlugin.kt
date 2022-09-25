import com.android.build.gradle.LibraryExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import xyz.stylianosgakis.shape.android.configureAndroidCompose

@Suppress("unused")
class LibraryComposeConventionPlugin : Plugin<Project> {
  override fun apply(target: Project) {
    with(target) {
      extensions.configure<LibraryExtension> {
        configureAndroidCompose(this)
      }
    }
  }
}
