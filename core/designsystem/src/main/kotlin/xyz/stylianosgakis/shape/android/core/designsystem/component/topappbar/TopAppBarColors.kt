package xyz.stylianosgakis.shape.android.core.designsystem.component.topappbar

import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
fun TopAppBarDefaults.transparentSmallTopAppBarColors(): TopAppBarColors {
  return smallTopAppBarColors(
    containerColor = Color.Transparent,
    navigationIconContentColor = LocalContentColor.current,
    titleContentColor = LocalContentColor.current,
    actionIconContentColor = LocalContentColor.current,
  )
}
