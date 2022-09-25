package xyz.stylianosgakis.shape.android.feature.workout

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource

@Composable
internal fun WorkoutRoute() {
  WorkoutScreen()
}

@Composable
private fun WorkoutScreen() {
  Text(stringResource(R.string.workout))
}
