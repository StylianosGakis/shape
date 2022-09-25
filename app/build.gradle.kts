@Suppress("DSL_SCOPE_VIOLATION")
plugins {
  id("shape.android.application")
  id("shape.android.application.compose")
  alias(libs.plugins.whetstone)
}

whetstone {
  addOns.compose.set(true)
}

kapt {
  useBuildCache = false
}

android {
  namespace = "xyz.stylianosgakis.shape.android.app"
  defaultConfig {
    applicationId = "xyz.stylianosgakis.shape"
    versionCode = 1
    versionName = "0.0.1"

    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

    vectorDrawables.useSupportLibrary = true
  }

  buildTypes {
    @Suppress("UNUSED_VARIABLE")
    val debug by getting {
      applicationIdSuffix = ".debug"
    }

    @Suppress("UNUSED_VARIABLE")
    val release by getting {
      isMinifyEnabled = true
      proguardFiles(
        getDefaultProguardFile("proguard-android-optimize.txt"),
        "proguard-rules.pro",
      )

      // To publish on the Play store a private signing key is required, but to allow anyone
      // who clones the code to sign and run the release variant, use the debug signing key.
      signingConfig = signingConfigs.getByName("debug")
    }
  }

  packagingOptions {
    resources {
      excludes += "/META-INF/{AL2.0,LGPL2.1}"
      excludes += "DebugProbesKt.bin"
    }
  }

  @Suppress("UnstableApiUsage")
  testOptions {
    unitTests {
      isIncludeAndroidResources = true
    }
  }
}

dependencies {
  implementation(projects.core.designsystem)
  implementation(projects.core.navigation)
  implementation(projects.core.theme)
  implementation(projects.database.android)
  implementation(projects.feature.home)
  implementation(projects.feature.library)
  implementation(projects.feature.profile)
  implementation(projects.feature.settings)
  implementation(projects.feature.workout)

  implementation(libs.accompanist.navigation.animation)
  implementation(libs.accompanist.systemUiController)
  implementation(libs.androidx.compose.animation)
  implementation(libs.androidx.compose.foundation)
  implementation(libs.androidx.compose.material3)
  implementation(libs.androidx.compose.material3WindowSizeClass)
  implementation(libs.androidx.compose.materialIconsExtended)
  implementation(libs.androidx.compose.runtime)
  implementation(libs.androidx.compose.ui)
  implementation(libs.androidx.core.splashScreen)
  implementation(libs.androidx.lifecycle.common)
  implementation(libs.androidx.lifecycle.compose)
  implementation(libs.androidx.lifecycle.process)
  implementation(libs.androidx.lifecycle.runtime)
  implementation(libs.androidx.lifecycle.viewModel)
  implementation(libs.androidx.other.activityCompose)
  implementation(libs.coroutines.android)

  testImplementation(libs.coroutines.test)
  testImplementation(libs.junit)

  androidTestImplementation(libs.androidx.compose.navigation.testing)
  androidTestImplementation(libs.androidx.compose.uiTest)
  androidTestImplementation(libs.androidx.compose.uiTestJunit4)
  androidTestImplementation(libs.androidx.test.espresso.core)
  androidTestImplementation(libs.androidx.test.junit)
  androidTestImplementation(libs.androidx.test.rules)
  androidTestImplementation(libs.androidx.test.runner)

  debugImplementation(libs.androidx.compose.uiTooling)
  debugImplementation(libs.androidx.compose.uiTestManifest)
}
