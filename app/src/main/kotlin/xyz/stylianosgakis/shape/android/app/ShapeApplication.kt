package xyz.stylianosgakis.shape.android.app

import android.app.Application
import com.deliveryhero.whetstone.app.ApplicationComponent
import com.deliveryhero.whetstone.app.ApplicationComponentOwner
import com.deliveryhero.whetstone.app.ContributesAppInjector
import logcat.AndroidLogcatLogger
import logcat.LogPriority
import xyz.stylianosgakis.shape.android.app.di.DaggerAppComponent

@ContributesAppInjector(false)
class ShapeApplication : Application(), ApplicationComponentOwner {
  override val applicationComponent: ApplicationComponent by lazy {
    DaggerAppComponent.factory().create(this)
  }

  override fun onCreate() {
    super.onCreate()
    AndroidLogcatLogger.installOnDebuggableApp(this, minPriority = LogPriority.VERBOSE)
  }
}
