package xyz.stylianosgakis.shape.android.app.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.AnimatedNavHost
import xyz.stylianosgakis.shape.android.core.navigation.ShapeNavigationDestination
import xyz.stylianosgakis.shape.android.feature.home.navigation.HomeDestination
import xyz.stylianosgakis.shape.android.feature.home.navigation.homeGraph
import xyz.stylianosgakis.shape.android.feature.library.navigation.libraryGraph
import xyz.stylianosgakis.shape.android.feature.profile.navigation.profileGraph
import xyz.stylianosgakis.shape.android.feature.settings.navigation.SettingsDestination
import xyz.stylianosgakis.shape.android.feature.settings.navigation.settingsGraph
import xyz.stylianosgakis.shape.android.feature.workout.navigation.WorkoutDestination
import xyz.stylianosgakis.shape.android.feature.workout.navigation.workoutGraph

@Composable
fun ShapeNavHost(
  navController: NavHostController,
  navigateToDestination: (ShapeNavigationDestination, String?) -> Unit,
  navigateBack: () -> Unit,
  modifier: Modifier = Modifier,
  startDestination: String = HomeDestination.route,
) {
  AnimatedNavHost(
    navController = navController,
    startDestination = startDestination,
    modifier = modifier,
  ) {
    homeGraph(
      navigateToWorkout = {
        navigateToDestination(WorkoutDestination, null)
      },
    )
    libraryGraph()
    profileGraph(
      navigateToSettings = {
        navigateToDestination(SettingsDestination, null)
      },
      nestedGraphs = {
        settingsGraph(navigateBack)
      },
    )
    workoutGraph()
  }
}
