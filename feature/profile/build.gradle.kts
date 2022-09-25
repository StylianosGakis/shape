plugins {
  id("shape.android.library")
  id("shape.android.library.compose")
  @Suppress("DSL_SCOPE_VIOLATION")
  alias(libs.plugins.whetstone)
}

android {
  namespace = "xyz.stylianosgakis.shape.android.feature.profile"
}

whetstone {
  generateDaggerFactories.set(true)
  addOns.compose.set(true)
}

dependencies {
  implementation(projects.core.designsystem)
  implementation(projects.core.navigation)
  implementation(projects.database.android)

  implementation(libs.accompanist.navigation.animation)
  implementation(libs.androidx.compose.material3)
  implementation(libs.kotlinx.immutableCollections)
}
