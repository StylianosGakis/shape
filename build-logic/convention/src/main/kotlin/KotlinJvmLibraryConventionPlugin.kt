import org.gradle.accessors.dm.LibrariesForLibs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.the
import xyz.stylianosgakis.shape.android.configureKotlinJvmOptions

@Suppress("unused")
class KotlinJvmLibraryConventionPlugin : Plugin<Project> {
  override fun apply(target: Project) {
    with(target) {
      val libs = the<LibrariesForLibs>()
      with(pluginManager) {
        apply(libs.plugins.kotlinJvm.get().pluginId)
      }

      tasks.withType(org.jetbrains.kotlin.gradle.tasks.KotlinCompile::class.java).configureEach {
        configureKotlinJvmOptions(this)
      }
    }
  }
}
