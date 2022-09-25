plugins {
  id("shape.android.library")
}

android {
  namespace = "xyz.stylianosgakis.shape.android.core.navigation"
}

dependencies {
  api(libs.androidx.navigation.common)
}
