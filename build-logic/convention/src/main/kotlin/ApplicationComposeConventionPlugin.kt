import com.android.build.gradle.internal.dsl.BaseAppModuleExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import xyz.stylianosgakis.shape.android.configureAndroidCompose

@Suppress("unused")
class ApplicationComposeConventionPlugin : Plugin<Project> {
  override fun apply(target: Project) {
    with(target) {
      extensions.configure<BaseAppModuleExtension> {
        configureAndroidCompose(this)
      }
    }
  }
}
