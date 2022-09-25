package xyz.stylianosgakis.shape.android

import com.android.build.api.dsl.CommonExtension
import org.gradle.accessors.dm.LibrariesForLibs
import org.gradle.api.JavaVersion
import org.gradle.api.Project
import org.gradle.api.plugins.ExtensionAware
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.the
import org.jetbrains.kotlin.gradle.dsl.KotlinCommonOptions
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmOptions
import org.jetbrains.kotlin.gradle.plugin.KotlinCompilation
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

private fun CommonExtension<*, *, *, *>.kotlinOptions(block: KotlinJvmOptions.() -> Unit) {
  (this as ExtensionAware).extensions.configure("kotlinOptions", block)
}

internal fun Project.configureKotlinAndroid(
  commonExtension: CommonExtension<*, *, *, *>,
) {
  val libs = the<LibrariesForLibs>()

  commonExtension.apply {
    compileSdk = libs.versions.compileSdkVersion.get().toInt()

    defaultConfig {
      minSdk = libs.versions.minSdkVersion.get().toInt()
    }

    compileOptions {
      isCoreLibraryDesugaringEnabled = true
      sourceCompatibility = JavaVersion.VERSION_17
      targetCompatibility = JavaVersion.VERSION_17
    }
  }

  dependencies {
    add("coreLibraryDesugaring", libs.coreLibraryDesugaring.get())
    add("implementation", libs.square.logcat.get())
  }
}

internal fun Project.configureKotlinJvmOptions(
  commonExtension: CommonExtension<*, *, *, *>,
) {
  commonExtension.kotlinOptions {
    configureKotlinJvmOptions(project)
  }
}

@Suppress("unused")
internal fun Project.configureKotlinJvmOptions(kotlinCompile: KotlinCompile) {
  val project = this
  kotlinCompile.kotlinOptions {
    this.configureKotlinJvmOptions(project)
  }
}

internal fun Project.configureKotlinMultiplatformOptions(kotlinCompilation: KotlinCompilation<KotlinCommonOptions>) {
  val project = this
  kotlinCompilation.kotlinOptions {
    configureKotlinCommonOptions(project)
  }
}

private fun KotlinJvmOptions.configureKotlinJvmOptions(
  project: Project,
) {
  configureKotlinCommonOptions(project)
  jvmTarget = JavaVersion.VERSION_17.toString()
}

private fun KotlinCommonOptions.configureKotlinCommonOptions(
  project: Project,
) {
  // Treat all Kotlin warnings as errors (disabled by default)
  allWarningsAsErrors = project.properties["warningsAsErrors"] as? Boolean ?: false

  freeCompilerArgs = freeCompilerArgs + listOf(
    "-Xjvm-default=all",
    "-opt-in=androidx.compose.animation.ExperimentalAnimationApi",
    "-opt-in=androidx.compose.foundation.layout.ExperimentalLayoutApi",
    "-opt-in=androidx.compose.material3.ExperimentalMaterial3Api",
    "-opt-in=androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi",
    "-opt-in=kotlin.Experimental",
    "-opt-in=kotlin.RequiresOptIn",
    "-opt-in=kotlinx.coroutines.ExperimentalCoroutinesApi",
    "-opt-in=kotlinx.coroutines.FlowPreview",
  )
}
