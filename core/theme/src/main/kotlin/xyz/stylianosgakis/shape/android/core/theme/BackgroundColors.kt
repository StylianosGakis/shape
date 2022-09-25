package xyz.stylianosgakis.shape.android.core.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.graphics.Color

object BackgroundColors {
  private const val backgroundAlpha = 0.12f

  private val home: Color
    @ReadOnlyComposable
    @Composable
    get() = MaterialTheme.colorScheme.primary.copy(alpha = backgroundAlpha)

  private val profile: Color
    @ReadOnlyComposable
    @Composable
    get() = MaterialTheme.colorScheme.secondary.copy(alpha = backgroundAlpha)

  private val library: Color
    @ReadOnlyComposable
    @Composable
    get() = MaterialTheme.colorScheme.tertiary.copy(alpha = backgroundAlpha)

  @ReadOnlyComposable
  @Composable
  fun fromIndex(index: Int): Color {
    if (isSystemInDarkTheme()) return Color.Unspecified
    return when (index) {
      0 -> home
      1 -> library
      2 -> profile
      else -> Color.Unspecified
    }
  }
}
