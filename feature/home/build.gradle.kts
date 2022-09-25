plugins {
  id("shape.android.library")
  id("shape.android.library.compose")
//  @Suppress("DSL_SCOPE_VIOLATION")
//  alias(libs.plugins.molecule)
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
