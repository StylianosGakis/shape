@Suppress("DSL_SCOPE_VIOLATION")
plugins {
  alias(libs.plugins.android.application) apply false
  alias(libs.plugins.android.library) apply false
  alias(libs.plugins.kotlinAndroid) apply false
  alias(libs.plugins.kotlinJvm) apply false
  alias(libs.plugins.kotlinKapt) apply false
  alias(libs.plugins.kotlinMultiPlatform) apply false
  alias(libs.plugins.kotlinter) apply false
  alias(libs.plugins.dependencyAnalysis)
}

apply {
  from(file("gradle/projectDependencyGraph.gradle"))
}
