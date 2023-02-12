plugins {
  id("shape.android.library")
  id("shape.ktlint")
  @Suppress("DSL_SCOPE_VIOLATION")
  alias(libs.plugins.whetstone)
}

android {
  namespace = "xyz.stylianosgakis.shape.core.databaseandroid"
// todo see if this can be put on every android library and have it all still work
//  variantFilter = Action {
//    if (buildType.name.contains("debug")) {
//      ignore = true
//    }
//  }
}

dependencies {
  api(projects.database.kmp)

  api(libs.sqldelight.androidDriver)
}
