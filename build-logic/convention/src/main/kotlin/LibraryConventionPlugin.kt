import com.android.build.gradle.LibraryExtension
import org.gradle.accessors.dm.LibrariesForLibs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.the
import xyz.stylianosgakis.shape.android.configureKotlinAndroid
import xyz.stylianosgakis.shape.android.configureKotlinJvmOptions

@Suppress("unused")
class LibraryConventionPlugin : Plugin<Project> {
  override fun apply(target: Project) {
    with(target) {
      val libs = the<LibrariesForLibs>()
      with(pluginManager) {
        apply(libs.plugins.android.library.get().pluginId)
        apply(libs.plugins.kotlinAndroid.get().pluginId)
      }

      extensions.configure<LibraryExtension> {
        configureKotlinAndroid(this)
        configureKotlinJvmOptions(this)
        defaultConfig.targetSdk = libs.versions.targetSdkVersion.get().toInt()
        buildFeatures {
          buildConfig = false
          aidl = false
          renderScript = false
          resValues = false
          shaders = false
        }
      }
    }
  }
}
