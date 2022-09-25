package xyz.stylianosgakis.shape.android.app

import android.content.res.Resources
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.navigation.testing.TestNavHostController
import androidx.test.espresso.Espresso
import androidx.test.espresso.NoActivityResumedException
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.accompanist.navigation.animation.AnimatedComposeNavigator
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import xyz.stylianosgakis.shape.android.app.ui.ShapeApp
import xyz.stylianosgakis.shape.android.app.ui.ShapeAppState
import xyz.stylianosgakis.shape.android.feature.home.R as homeR
import xyz.stylianosgakis.shape.android.feature.library.R as libraryR
import xyz.stylianosgakis.shape.android.feature.profile.R as profileR

@RunWith(AndroidJUnit4::class)
class NavigationTest {

  @get:Rule
  val composeTestRule = createComposeRule()

  private lateinit var navController: TestNavHostController
  private lateinit var resources: Resources

  @Before
  fun setupNavHost() {
    val windowSizeClass = WindowSizeClass.calculateFromSize(DpSize(500.dp, 500.dp))
    composeTestRule.setContent {
      navController = TestNavHostController(LocalContext.current)
      navController.navigatorProvider.addNavigator(AnimatedComposeNavigator())
      resources = LocalContext.current.resources
      ShapeApp(windowSizeClass, ShapeAppState(navController, windowSizeClass))
    }
  }

  @Test
  fun navhost_startsAtHomeDestination() {
    composeTestRule
      .onNodeWithText(resources.getString(homeR.string.plan))
      .assertExists()
  }

  @Test(expected = NoActivityResumedException::class)
  fun homeDestination_back_quitsApp() {
    composeTestRule.apply {
      onNodeWithText(resources.getString(libraryR.string.library)).performClick()
      onNodeWithText(resources.getString(profileR.string.profile)).performClick()
      Espresso.pressBack()
      Espresso.pressBack()
    }
  }
}
