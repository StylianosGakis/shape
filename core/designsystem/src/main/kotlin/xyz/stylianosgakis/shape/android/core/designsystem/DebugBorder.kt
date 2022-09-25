package xyz.stylianosgakis.shape.android.core.designsystem

import androidx.compose.foundation.border
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Suppress("unused")
fun Modifier.debugBorder(
  width: Dp = 1.dp,
  color: Color = Color.Red,
): Modifier {
  return border(width, color)
}
