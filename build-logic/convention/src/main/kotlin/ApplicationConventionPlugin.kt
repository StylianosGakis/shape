import com.android.build.gradle.internal.dsl.BaseAppModuleExtension
import org.gradle.accessors.dm.LibrariesForLibs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.the
import xyz.stylianosgakis.shape.android.configureKotlinAndroid
import xyz.stylianosgakis.shape.android.configureKotlinJvmOptions

@Suppress("unused")
class ApplicationConventionPlugin : Plugin<Project> {
  override fun apply(target: Project) {
    with(target) {
      val libs = the<LibrariesForLibs>()
      with(pluginManager) {
        apply(libs.plugins.android.application.get().pluginId)
        apply(libs.plugins.kotlinAndroid.get().pluginId)
      }

      extensions.configure<BaseAppModuleExtension> {
        configureKotlinAndroid(this)
        configureKotlinJvmOptions(this)
        defaultConfig.targetSdk = libs.versions.targetSdkVersion.get().toInt()
      }
    }
  }
}
