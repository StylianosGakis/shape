package xyz.stylianosgakis.shape.android.core.designsystem.icons

import androidx.annotation.DrawableRes
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource

sealed interface ShapeIcon {

  @Immutable
  class ImageVectorIcon(val imageVector: ImageVector) : ShapeIcon

  @Immutable
  class DrawableResourceIcon(@DrawableRes val id: Int) : ShapeIcon

  companion object {
    operator fun invoke(imageVector: ImageVector): ShapeIcon {
      return ImageVectorIcon(imageVector)
    }

    operator fun invoke(@DrawableRes id: Int): ShapeIcon {
      return DrawableResourceIcon(id)
    }
  }
}

@Composable
fun ShapeIcon(
  icon: ShapeIcon,
  modifier: Modifier = Modifier,
  tint: Color = LocalContentColor.current,
) {
  when (icon) {
    is ShapeIcon.ImageVectorIcon -> Icon(
      imageVector = icon.imageVector,
      contentDescription = null,
      modifier = modifier,
      tint = tint,
    )
    is ShapeIcon.DrawableResourceIcon -> Icon(
      painter = painterResource(icon.id),
      contentDescription = null,
      modifier = modifier,
      tint = tint,
    )
  }
}
