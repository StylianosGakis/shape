package xyz.stylianosgakis.shape.android.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import xyz.stylianosgakis.shape.android.app.ui.ShapeApp

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    installSplashScreen()
    super.onCreate(savedInstanceState)
    WindowCompat.setDecorFitsSystemWindows(window, false)
    setContent {
      ShapeApp(calculateWindowSizeClass(this))
    }
    // Maybe add accompanist setSystemBarsColor(
    // color = Color.Transparent,
    // darkIcons = useDarkIcons <=> MaterialTheme.colors.isLight
    // isNavigationBarContrastEnforced = false <-- this to remove the nav background
    // )
  }
}
