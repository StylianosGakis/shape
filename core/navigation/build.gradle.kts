plugins {
  id("shape.android.library")
  id("shape.ktlint")
}

android {
  namespace = "xyz.stylianosgakis.shape.android.core.navigation"
}

dependencies {
  api(libs.androidx.navigation.common)
}
