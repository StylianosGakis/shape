plugins {
  id("shape.android.library")
  id("shape.android.library.compose")
  id("shape.ktlint")
//  @Suppress("DSL_SCOPE_VIOLATION")
//  alias(libs.plugins.molecule) // todo try molecule
}

android {
  namespace = "xyz.stylianosgakis.shape.android.feature.home"
}

dependencies {
  implementation(projects.core.designsystem)
  implementation(projects.core.navigation)

  implementation(libs.androidx.compose.material3)
  implementation(libs.accompanist.navigation.animation)
}
