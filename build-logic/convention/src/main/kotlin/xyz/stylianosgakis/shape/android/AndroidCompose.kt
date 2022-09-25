package xyz.stylianosgakis.shape.android

import com.android.build.api.dsl.CommonExtension
import org.gradle.accessors.dm.LibrariesForLibs
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.the

/**
 * Configure Compose-specific options
 */
internal fun Project.configureAndroidCompose(
  commonExtension: CommonExtension<*, *, *, *>,
) {
  val libs = the<LibrariesForLibs>()

  commonExtension.apply {
    buildFeatures {
      compose = true
    }

    composeOptions {
      kotlinCompilerExtensionVersion = libs.versions.androidx.composeCompiler.get()
    }

    dependencies {
      val composeBom = platform(libs.androidx.compose.bom)
      add("implementation", composeBom)
      add("androidTestImplementation", composeBom)
    }
  }
}
