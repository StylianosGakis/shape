plugins {
  `kotlin-dsl`
}

group = "xyz.stylianosgakis.shape.android.buildlogic"

java {
  sourceCompatibility = JavaVersion.VERSION_17
  targetCompatibility = JavaVersion.VERSION_17
}

dependencies {
  compileOnly(libs.android.gradlePlugin)
  compileOnly(libs.kotlin.gradlePlugin)
  compileOnly(libs.kotlinter.gradlePlugin)

  // Enables using type-safe accessors to reference plugins from the [plugins] block defined in version catalogs.
  // Context: https://github.com/gradle/gradle/issues/15383#issuecomment-779893192
  implementation(files(libs.javaClass.superclass.protectionDomain.codeSource.location))
}

gradlePlugin {
  plugins {
    fun createPlugin(id: String, implementationClass: String) {
      plugins.create(id) {
        this.id = id
        this.implementationClass = implementationClass
      }
    }
    createPlugin("shape.android.application", "ApplicationConventionPlugin")
    createPlugin("shape.android.application.compose", "ApplicationComposeConventionPlugin")
    createPlugin("shape.android.library", "LibraryConventionPlugin")
    createPlugin("shape.android.library.compose", "LibraryComposeConventionPlugin")
    createPlugin("shape.kotlin.library.jvm", "KotlinJvmLibraryConventionPlugin")
    createPlugin("shape.kotlin.library.multiplatform", "KotlinMultiplatformLibraryConventionPlugin")
    createPlugin("shape.ktlint", "KotlinterConventionPlugin")
  }
}
