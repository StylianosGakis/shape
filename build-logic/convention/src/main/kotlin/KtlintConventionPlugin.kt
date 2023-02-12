import org.gradle.accessors.dm.LibrariesForLibs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.the
import org.gradle.kotlin.dsl.withType
import org.jmailen.gradle.kotlinter.KotlinterExtension

class KotlinterConventionPlugin : Plugin<Project> {
  override fun apply(target: Project) {
    with(target) {
      val libs = the<LibrariesForLibs>()
      with(pluginManager) {
        apply(libs.plugins.kotlinter.get().pluginId)
      }

      extensions.configure<KotlinterExtension> {
        ignoreFailures = false
        reporters = arrayOf("checkstyle")
      }

      tasks.withType<org.jmailen.gradle.kotlinter.tasks.LintTask>().configureEach {
        exclude { it.file.path.contains("generated/") }
        reports.set(
          mapOf(
            "checkstyle" to rootDir.resolve("build/reports/ktlint/${project.path}.xml"),
          ),
        )
      }
      tasks.withType<org.jmailen.gradle.kotlinter.tasks.FormatTask>().configureEach {
        exclude { it.file.path.contains("generated/") }
      }

      tasks.register("ktlintCheck") {
        dependsOn(tasks.withType<org.jmailen.gradle.kotlinter.tasks.LintTask>())
      }

      tasks.register("ktlintFormat") {
        dependsOn(tasks.withType<org.jmailen.gradle.kotlinter.tasks.FormatTask>())
      }
    }
  }
}
