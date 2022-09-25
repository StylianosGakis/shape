@file:Suppress("UnstableApiUsage")

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

pluginManagement {
  includeBuild("build-logic")
  repositories {
    google()
    gradlePluginPortal()
    mavenCentral()
  }
}

dependencyResolutionManagement {
  repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
  repositories {
    google()
    mavenCentral()
    mavenLocal()
  }
}

rootProject.name = "shape"

include(":app")
include(":core:designsystem")
include(":core:navigation")
include(":core:theme")
include(":database:android")
include(":database:kmp")
include(":feature:home")
include(":feature:library")
include(":feature:profile")
include(":feature:settings")
include(":feature:workout")
