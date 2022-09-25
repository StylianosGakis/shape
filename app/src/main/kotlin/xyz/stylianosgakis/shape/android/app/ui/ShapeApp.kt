package xyz.stylianosgakis.shape.android.app.ui

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.layout.layout
import kotlinx.coroutines.launch
import xyz.stylianosgakis.shape.android.app.navigation.ShapeNavHost
import xyz.stylianosgakis.shape.android.core.designsystem.component.ShapeGradientBackground
import xyz.stylianosgakis.shape.android.core.theme.ShapeTheme
import kotlin.math.roundToInt

@Composable
fun ShapeApp(
  windowSizeClass: WindowSizeClass,
  appState: ShapeAppState = rememberShapeAppState(windowSizeClass),
) {
  ShapeTheme {
    Column {
      Row(
        Modifier
          .windowInsetsPadding(WindowInsets.safeDrawing.only(WindowInsetsSides.Horizontal))
          .weight(1f),
      ) {
        if (appState.showNavRail) {
          ShapeNavRail(
            destinations = appState.topLevelDestinations,
            onNavigateToDestination = appState::navigate,
            currentDestination = appState.currentDestination,
            modifier = Modifier.safeDrawingPadding(),
          )
        }
        ShapeGradientBackground(
          appState.currentBackgroundGradientColor,
          Modifier
            .fillMaxHeight()
            .weight(1f),
        ) {
          ShapeNavHost(
            navController = appState.navController,
            navigateBack = appState::navigateBack,
            navigateToDestination = appState::navigate,
            modifier = Modifier.matchParentSize(),
          )
        }
      }
      ShapeBottomBar(
        destinations = appState.topLevelDestinations,
        navigateToDestination = appState::navigate,
        currentDestination = appState.currentDestination,
        modifier = Modifier.composed {
          val scope = rememberCoroutineScope()
          val show = appState.showBottomBar
          val showingPercentage = if (show) 1f else 0f

          val animation = remember { Animatable(1f) }

          LaunchedEffect(showingPercentage) {
            if (animation.targetValue != showingPercentage) {
              scope.launch {
                animation.animateTo(showingPercentage, spring(stiffness = Spring.StiffnessLow))
              }
            }
          }
          layout { measurable, constraints ->
            val placeable = measurable.measure(constraints)
            layout(placeable.width, (placeable.height * animation.value).roundToInt()) {
              placeable.place(0, 0)
            }
          }
        },
      )
    }
  }
}
