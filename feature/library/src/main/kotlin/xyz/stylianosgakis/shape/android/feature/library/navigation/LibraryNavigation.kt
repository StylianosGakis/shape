package xyz.stylianosgakis.shape.android.feature.library.navigation

import androidx.navigation.NavGraphBuilder
import com.google.accompanist.navigation.animation.composable
import xyz.stylianosgakis.shape.android.core.navigation.ShapeNavigationDestination
import xyz.stylianosgakis.shape.android.feature.library.LibraryRoute

object LibraryDestination : ShapeNavigationDestination(
  route = "library_route",
  destination = "library_destination",
  showNav = true,
)

fun NavGraphBuilder.libraryGraph() {
  composable(
    route = LibraryDestination.route,
    arguments = LibraryDestination.arguments,
  ) {
    LibraryRoute()
  }
}
