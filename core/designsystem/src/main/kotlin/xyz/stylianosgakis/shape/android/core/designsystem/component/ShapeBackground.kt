package xyz.stylianosgakis.shape.android.core.designsystem.component

import androidx.compose.animation.VectorConverter
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationSpec
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.material3.LocalAbsoluteTonalElevation
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch
import xyz.stylianosgakis.shape.android.core.theme.LocalBackgroundTheme
import kotlin.math.tan

@Composable
fun ShapeBackground(
  modifier: Modifier = Modifier,
  content: @Composable () -> Unit,
) {
  val color = LocalBackgroundTheme.current.color
  val tonalElevation = LocalBackgroundTheme.current.tonalElevation
  Surface(
    color = if (color == Color.Unspecified) Color.Transparent else color,
    tonalElevation = if (tonalElevation == Dp.Unspecified) 0.dp else tonalElevation,
    modifier = modifier,
  ) {
    CompositionLocalProvider(LocalAbsoluteTonalElevation provides 0.dp) {
      content()
    }
  }
}

@Composable
fun ShapeGradientBackground(
  topColor: Color,
  modifier: Modifier = Modifier,
  bottomColor: Color = Color.Unspecified,
  content: @Composable BoxScope.() -> Unit,
) {
  val currentTopColor by animateGradientBackgroundColorAsState(
    topColor,
    spring(stiffness = Spring.StiffnessLow),
  )
  val currentBottomColor by rememberUpdatedState(bottomColor)
  ShapeBackground(modifier) {
    Box(
      Modifier.drawWithCache {
        // Compute the start and end coords such that the gradients are angled 11.06 degrees off the vertical axis
        val offset = size.height * tan(Math.toRadians(11.06).toFloat())
        val start = Offset((size.width / 2) + (offset / 2), 0f)
        val end = Offset((size.width / 2) - (offset / 2), size.height)

        // Create the top gradient that fades out after the halfway point vertically
        val topGradient = Brush.linearGradient(
          0f to if (currentTopColor == Color.Unspecified) {
            Color.Transparent
          } else {
            currentTopColor
          },
          0.724f to Color.Transparent,
          start = start,
          end = end,
        )
        // Create the bottom gradient that fades in before the halfway point vertically
        val bottomGradient = Brush.linearGradient(
          0.2552f to Color.Transparent,
          1f to if (currentBottomColor == Color.Unspecified) {
            Color.Transparent
          } else {
            currentBottomColor
          },
          start = start,
          end = end,
        )
        onDrawBehind {
          // There is overlap here, so order is important
          drawRect(topGradient)
          drawRect(bottomGradient)
        }
      },
    ) {
      content()
    }
  }
}

/**
 * Custom animateColorAsState in order to snap to the color when it comes from or is going towards an unspecified color.
 * It only animates when going from a real color to another.
 */
@Composable
private fun animateGradientBackgroundColorAsState(
  targetValue: Color,
  animationSpec: AnimationSpec<Color> = spring(),
  label: String = "ColorAnimation",
  finishedListener: ((Color) -> Unit)? = null,
): State<Color> {
  val converter = remember(targetValue.colorSpace) {
    (Color.VectorConverter)(targetValue.colorSpace)
  }
  val animatable = remember { Animatable(targetValue, converter, label = label) }
  val updatedFinishedListener by rememberUpdatedState(finishedListener)
  val updatedAnimationSpec by rememberUpdatedState(animationSpec)
  val channel = remember { Channel<Color>(Channel.CONFLATED) }
  SideEffect {
    channel.trySend(targetValue)
  }
  LaunchedEffect(channel) {
    for (target in channel) {
      val newTarget = channel.tryReceive().getOrNull() ?: target
      launch {
        if (newTarget == Color.Unspecified || animatable.value == Color.Unspecified) {
          animatable.snapTo(newTarget)
        } else if (newTarget != animatable.targetValue) {
          animatable.animateTo(newTarget, updatedAnimationSpec)
          updatedFinishedListener?.invoke(animatable.value)
        }
      }
    }
  }
  return animatable.asState()
}
