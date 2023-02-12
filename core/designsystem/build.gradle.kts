plugins {
  id("shape.android.library")
  id("shape.android.library.compose")
  id("shape.ktlint")
}

android {
  namespace = "xyz.stylianosgakis.shape.android.core.designsystem"
}

dependencies {
  implementation(projects.core.theme)

  implementation(libs.accompanist.navigation.animation)
  implementation(libs.androidx.compose.material3)
  implementation(libs.androidx.compose.materialIconsExtended)
  implementation(libs.androidx.compose.ui)
}
