package xyz.stylianosgakis.shape.android.feature.workout.navigation

import androidx.navigation.NavGraphBuilder
import com.google.accompanist.navigation.animation.composable
import xyz.stylianosgakis.shape.android.core.navigation.ShapeNavigationDestination
import xyz.stylianosgakis.shape.android.feature.workout.WorkoutRoute

object WorkoutDestination : ShapeNavigationDestination(
  route = "workout_route",
  destination = "workout_destination",
)

fun NavGraphBuilder.workoutGraph() {
  composable(route = WorkoutDestination.route) {
    WorkoutRoute()
  }
}
