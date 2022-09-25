package xyz.stylianosgakis.shape.android.feature.settings.navigation

import androidx.navigation.NavGraphBuilder
import com.deliveryhero.whetstone.compose.injectedViewModel
import com.google.accompanist.navigation.animation.composable
import xyz.stylianosgakis.shape.android.core.navigation.ShapeNavigationDestination
import xyz.stylianosgakis.shape.android.feature.settings.SettingsRoute
import xyz.stylianosgakis.shape.android.feature.settings.SettingsViewModel

object SettingsDestination : ShapeNavigationDestination(
  route = "settings_route",
  destination = "settings_destination",
  showNav = true,
)

fun NavGraphBuilder.settingsGraph(navigateBack: () -> Unit) {
  composable(
    route = SettingsDestination.route,
    arguments = SettingsDestination.arguments,
  ) {
    val settingsViewModel: SettingsViewModel = injectedViewModel()
    SettingsRoute(navigateBack, settingsViewModel)
  }
}
