package xyz.stylianosgakis.shape.android.app.ui

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import xyz.stylianosgakis.shape.android.app.navigation.TopLevelDestination
import xyz.stylianosgakis.shape.android.core.designsystem.component.navigation.ShapeNavigationRail
import xyz.stylianosgakis.shape.android.core.designsystem.component.navigation.ShapeNavigationRailItem
import xyz.stylianosgakis.shape.android.core.designsystem.icons.ShapeIcon

@Composable
fun ShapeNavRail(
  destinations: List<TopLevelDestination>,
  onNavigateToDestination: (TopLevelDestination) -> Unit,
  currentDestination: NavDestination?,
  modifier: Modifier = Modifier,
) {
  ShapeNavigationRail(modifier = modifier) {
    destinations.forEach { destination ->
      val selected = currentDestination?.hierarchy?.any { it.route == destination.route } == true
      ShapeNavigationRailItem(
        selected = selected,
        onClick = { onNavigateToDestination(destination) },
        icon = {
          val icon = if (selected) {
            destination.selectedIcon
          } else {
            destination.unselectedIcon
          }
          ShapeIcon(icon)
        },
        label = { Text(stringResource(destination.iconTextId)) },
      )
    }
  }
}
