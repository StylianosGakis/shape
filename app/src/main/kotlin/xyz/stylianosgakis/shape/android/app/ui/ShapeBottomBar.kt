package xyz.stylianosgakis.shape.android.app.ui

import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import xyz.stylianosgakis.shape.android.app.navigation.TopLevelDestination
import xyz.stylianosgakis.shape.android.core.designsystem.component.navigation.ShapeNavigationBar
import xyz.stylianosgakis.shape.android.core.designsystem.component.navigation.ShapeNavigationBarItem
import xyz.stylianosgakis.shape.android.core.designsystem.icons.ShapeIcon.DrawableResourceIcon
import xyz.stylianosgakis.shape.android.core.designsystem.icons.ShapeIcon.ImageVectorIcon

@Composable
internal fun ShapeBottomBar(
  destinations: List<TopLevelDestination>,
  navigateToDestination: (TopLevelDestination) -> Unit,
  currentDestination: NavDestination?,
  modifier: Modifier = Modifier,
) {
  Surface(modifier) {
    ShapeNavigationBar {
      destinations.forEach { destination ->
        val selected = currentDestination?.hierarchy?.any { it.route == destination.route } == true
        ShapeNavigationBarItem(
          selected = selected,
          onClick = { navigateToDestination(destination) },
          icon = {
            val icon = if (selected) {
              destination.selectedIcon
            } else {
              destination.unselectedIcon
            }
            when (icon) {
              is ImageVectorIcon -> Icon(
                imageVector = icon.imageVector,
                contentDescription = null,
              )
              is DrawableResourceIcon -> Icon(
                painter = painterResource(icon.id),
                contentDescription = null,
              )
            }
          },
          label = { Text(stringResource(destination.iconTextId)) },
        )
      }
    }
  }
}
