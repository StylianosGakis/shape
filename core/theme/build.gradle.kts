plugins {
  id("shape.android.library")
  id("shape.android.library.compose")
}

android {
  namespace = "xyz.stylianosgakis.shape.android.core.theme"
}

dependencies {
  implementation(libs.androidx.compose.foundation)
  implementation(libs.androidx.compose.material3)
  implementation(libs.androidx.compose.runtime)
}
