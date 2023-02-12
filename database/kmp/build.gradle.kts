plugins {
  id("shape.kotlin.library.multiplatform")
  id("shape.ktlint")
  @Suppress("DSL_SCOPE_VIOLATION")
  alias(libs.plugins.sqldelight)
}

sqldelight {
  database("ShapeDatabase") {
    packageName = "xyz.stylianosgakis.shape.database"
    dialect = "sqlite:3.25"
    schemaOutputDirectory = file("src/commonMain/sqldelight/xyz/stylianosgakis/shape/database/schema")
    verifyMigrations = true
  }
}

android {
  namespace = "xyz.stylianosgakis.shape.database"
}

kotlin {
  android()
  jvm()
  sourceSets {
    val commonMain by getting {
      dependencies {
        api(libs.sqldelight.coroutinesExtensions)
      }
    }

    @Suppress("UNUSED_VARIABLE")
    val androidMain by getting {
      dependsOn(commonMain)
    }

    @Suppress("UNUSED_VARIABLE")
    val jvmMain by getting {
      dependsOn(commonMain)
      dependencies {
        implementation(libs.sqldelight.jvmDriver)
      }
    }
  }
}
