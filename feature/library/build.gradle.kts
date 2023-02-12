plugins {
  id("shape.android.library")
  id("shape.android.library.compose")
  id("shape.ktlint")
}

android {
  namespace = "xyz.stylianosgakis.shape.android.feature.library"
}

dependencies {
  implementation(projects.core.designsystem)
  implementation(projects.core.navigation)

  implementation(libs.androidx.compose.material3)
  implementation(libs.accompanist.navigation.animation)
}
