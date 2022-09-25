package xyz.stylianosgakis.shape.android.feature.home.navigation

import androidx.navigation.NavGraphBuilder
import com.google.accompanist.navigation.animation.composable
import xyz.stylianosgakis.shape.android.core.navigation.ShapeNavigationDestination
import xyz.stylianosgakis.shape.android.feature.home.HomeRoute

object HomeDestination : ShapeNavigationDestination(
  route = "home_route",
  destination = "home_destination",
  showNav = true,
)

fun NavGraphBuilder.homeGraph(
  navigateToWorkout: () -> Unit,
) {
  composable(
    route = HomeDestination.route,
    arguments = HomeDestination.arguments,
  ) {
    HomeRoute(navigateToWorkout)
  }
}
