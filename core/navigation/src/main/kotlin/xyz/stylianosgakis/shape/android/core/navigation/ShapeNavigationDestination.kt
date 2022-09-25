package xyz.stylianosgakis.shape.android.core.navigation

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument

abstract class ShapeNavigationDestination(
  route: String,
  val destination: String,
  showNav: Boolean = false,
) {
  val route: String = "$route?showNav={showNav}"
  val arguments: List<NamedNavArgument> = listOf(
    navArgument("showNav") {
      defaultValue = showNav
      type = NavType.BoolType
    },
  )
}
