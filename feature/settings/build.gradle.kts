plugins {
  id("shape.android.library")
  id("shape.android.library.compose")
  id("shape.ktlint")
  @Suppress("DSL_SCOPE_VIOLATION")
  alias(libs.plugins.whetstone)
}

android {
  namespace = "xyz.stylianosgakis.shape.android.feature.settings"
}

whetstone {
  generateDaggerFactories.set(true)
  addOns.compose.set(true)
}

dependencies {
  implementation(projects.core.designsystem)
  implementation(projects.core.navigation)

  implementation(libs.accompanist.navigation.animation)
  implementation(libs.androidx.compose.material3)
  implementation(libs.androidx.compose.ui)
}
