package xyz.stylianosgakis.shape.android.feature.profile.navigation

import androidx.navigation.NavGraphBuilder
import com.deliveryhero.whetstone.compose.injectedViewModel
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.navigation
import xyz.stylianosgakis.shape.android.core.navigation.ShapeNavigationDestination
import xyz.stylianosgakis.shape.android.feature.profile.ProfileRoute
import xyz.stylianosgakis.shape.android.feature.profile.ProfileViewModel

object ProfileDestination : ShapeNavigationDestination(
  route = "profile_route",
  destination = "profile_destination",
  showNav = true,
)

fun NavGraphBuilder.profileGraph(
  navigateToSettings: () -> Unit,
  nestedGraphs: NavGraphBuilder.() -> Unit,
) {
  navigation(
    route = ProfileDestination.route,
    startDestination = ProfileDestination.destination,
  ) {
    composable(
      route = ProfileDestination.destination,
      arguments = ProfileDestination.arguments,
    ) {
      val viewModel: ProfileViewModel = injectedViewModel()
      ProfileRoute(navigateToSettings, viewModel)
    }
    nestedGraphs()
  }
}
