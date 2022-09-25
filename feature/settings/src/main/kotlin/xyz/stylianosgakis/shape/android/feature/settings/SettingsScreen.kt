package xyz.stylianosgakis.shape.android.feature.settings

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarDefaults.pinnedScrollBehavior
import androidx.compose.runtime.Composable
import xyz.stylianosgakis.shape.android.core.designsystem.component.topappbar.transparentSmallTopAppBarColors
import xyz.stylianosgakis.shape.android.core.designsystem.icons.ShapeIcons

@Composable
internal fun SettingsRoute(
  navigateBack: () -> Unit,
  settingsViewModel: SettingsViewModel,
) {
  SettingsScreen(navigateBack)
}

@Composable
private fun SettingsScreen(navigateBack: () -> Unit) {
  Column {
    SettingsTopAppBar(navigateBack)
    Text("SettingsScreen")
  }
}

@Composable
private fun SettingsTopAppBar(
  navigateBack: () -> Unit,
) {
//  MediumTopAppBar() {} todo
  TopAppBar(
    title = {
      Text("Settings")
    },
    navigationIcon = {
      IconButton(navigateBack) {
        Icon(ShapeIcons.Back, "Back")
      }
    },
    colors = TopAppBarDefaults.transparentSmallTopAppBarColors(),
    scrollBehavior = pinnedScrollBehavior(),
  )
}
