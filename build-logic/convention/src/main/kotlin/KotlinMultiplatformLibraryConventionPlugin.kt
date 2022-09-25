import com.android.build.gradle.LibraryExtension
import org.gradle.accessors.dm.LibrariesForLibs
import org.gradle.api.Action
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.the
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension
import xyz.stylianosgakis.shape.android.configureKotlinAndroid
import xyz.stylianosgakis.shape.android.configureKotlinMultiplatformOptions

@Suppress("unused")
class KotlinMultiplatformLibraryConventionPlugin : Plugin<Project> {
  override fun apply(target: Project) {
    with(target) {
      val libs = the<LibrariesForLibs>()
      with(pluginManager) {
        apply(libs.plugins.kotlinMultiPlatform.get().pluginId)
        apply(libs.plugins.android.library.get().pluginId)
      }

      extensions.configure<LibraryExtension> {
        configureKotlinAndroid(this)
        defaultConfig.targetSdk = libs.versions.targetSdkVersion.get().toInt()
        buildFeatures {
          buildConfig = false
          aidl = false
          renderScript = false
          resValues = false
          shaders = false
        }
      }
      kotlin {
        targets.configureEach {
          compilations.configureEach {
            configureKotlinMultiplatformOptions(this)
          }
        }
      }
    }
  }
}

private fun Project.kotlin(
  configure: Action<KotlinMultiplatformExtension>,
): Unit = (this as org.gradle.api.plugins.ExtensionAware).extensions.configure("kotlin", configure)
