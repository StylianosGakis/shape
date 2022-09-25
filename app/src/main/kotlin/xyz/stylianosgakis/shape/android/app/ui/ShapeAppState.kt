package xyz.stylianosgakis.shape.android.app.ui

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.windowsizeclass.WindowHeightSizeClass
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import logcat.logcat
import xyz.stylianosgakis.shape.android.app.navigation.TopLevelDestination
import xyz.stylianosgakis.shape.android.core.designsystem.icons.ShapeIcon
import xyz.stylianosgakis.shape.android.core.designsystem.icons.ShapeIcons
import xyz.stylianosgakis.shape.android.core.navigation.ShapeNavigationDestination
import xyz.stylianosgakis.shape.android.core.theme.BackgroundColors
import xyz.stylianosgakis.shape.android.feature.home.navigation.HomeDestination
import xyz.stylianosgakis.shape.android.feature.library.navigation.LibraryDestination
import xyz.stylianosgakis.shape.android.feature.profile.navigation.ProfileDestination
import xyz.stylianosgakis.shape.android.feature.home.R as homeR
import xyz.stylianosgakis.shape.android.feature.library.R as libraryR
import xyz.stylianosgakis.shape.android.feature.profile.R as profileR

@Composable
fun rememberShapeAppState(
  windowSizeClass: WindowSizeClass,
  navController: NavHostController = rememberAnimatedNavController(),
): ShapeAppState {
  return remember(windowSizeClass, navController) {
    ShapeAppState(navController, windowSizeClass)
  }
}

@Stable
class ShapeAppState(
  val navController: NavHostController,
  private val windowSizeClass: WindowSizeClass,
) {
  val currentDestination: NavDestination?
    @Composable get() = navController.currentBackStackEntryAsState().value?.destination

  private val showNav: Boolean
    @Composable get() {
      return (currentDestination?.arguments?.get("showNav")?.defaultValue as? Boolean) ?: false
    }

  private val isBottomBarSize: Boolean
    get() = windowSizeClass.widthSizeClass == WindowWidthSizeClass.Compact ||
      windowSizeClass.heightSizeClass == WindowHeightSizeClass.Compact

  private val isNavRailSize: Boolean
    get() = !isBottomBarSize

  val showBottomBar: Boolean
    @Composable get() = showNav && isBottomBarSize
  val showNavRail: Boolean
    @Composable get() = showNav && isNavRailSize

  val topLevelDestinations: List<TopLevelDestination> = listOf(
    TopLevelDestination(
      route = HomeDestination.route.substringBefore("?"),
      destination = HomeDestination.destination,
      iconTextId = homeR.string.workout,
      selectedIcon = ShapeIcon(ShapeIcons.WorkoutSelected),
      unselectedIcon = ShapeIcon(ShapeIcons.Workout),
    ),
    TopLevelDestination(
      route = LibraryDestination.route.substringBefore("?"),
      destination = LibraryDestination.destination,
      iconTextId = libraryR.string.library,
      selectedIcon = ShapeIcon(ShapeIcons.Library),
    ),
    TopLevelDestination(
      route = ProfileDestination.route.substringBefore("?"),
      destination = ProfileDestination.destination,
      iconTextId = profileR.string.profile,
      selectedIcon = ShapeIcon(ShapeIcons.ProfileSelected),
      unselectedIcon = ShapeIcon(ShapeIcons.Profile),
    ),
  )

  val currentBackgroundGradientColor: Color
    @Composable
    get() {
      val isDarkMode = isSystemInDarkTheme()
      val currentDestination = currentDestination?.route
      return if (isDarkMode) {
        Color.Unspecified
      } else {
        topLevelDestinations
          .indexOfFirst { topLevelDestination ->
            when (currentDestination) {
              topLevelDestination.route -> true
              topLevelDestination.destination -> true
              else -> false
            }
          }
          .let { index ->
            BackgroundColors.fromIndex(index)
          }
      }
    }

  fun navigate(destination: ShapeNavigationDestination, route: String? = null) {
    logcat { "Stelios: Gonna navigate to: route ?: destination.route||${route ?: destination.route}" }
    if (destination is TopLevelDestination) {
      navController.navigate(route ?: destination.route) {
        // Pop up to the start destination of the graph to
        // avoid building up a large stack of destinations
        // on the back stack as users select items
        popUpTo(navController.graph.findStartDestination().id) {
          saveState = true
        }
        // Avoid multiple copies of the same destination when
        // re-selecting the same item
        launchSingleTop = true
        // Restore state when re-selecting a previously selected item
        restoreState = true
      }
    } else {
      navController.navigate(route ?: destination.route)
    }
  }

  fun navigateBack() {
    navController.popBackStack()
  }
}
