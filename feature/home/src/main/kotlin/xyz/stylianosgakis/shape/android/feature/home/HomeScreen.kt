package xyz.stylianosgakis.shape.android.feature.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource

@Composable
internal fun HomeRoute(
  navigateToWorkout: () -> Unit,
) {
  HomeScreen(navigateToWorkout)
}

@Composable
private fun HomeScreen(
  navigateToWorkout: () -> Unit,
) {
  Column {
    Text(stringResource(R.string.plan))
    Button(
      navigateToWorkout,
      Modifier.fillMaxWidth(),
    ) {
      Text(stringResource(R.string.workout))
    }
  }
}
