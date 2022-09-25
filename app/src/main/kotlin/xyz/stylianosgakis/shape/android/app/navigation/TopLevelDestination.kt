package xyz.stylianosgakis.shape.android.app.navigation

import xyz.stylianosgakis.shape.android.core.designsystem.icons.ShapeIcon
import xyz.stylianosgakis.shape.android.core.navigation.ShapeNavigationDestination

class TopLevelDestination(
  route: String,
  destination: String,
  val iconTextId: Int,
  val selectedIcon: ShapeIcon,
  val unselectedIcon: ShapeIcon = selectedIcon,
) : ShapeNavigationDestination(route, destination)
