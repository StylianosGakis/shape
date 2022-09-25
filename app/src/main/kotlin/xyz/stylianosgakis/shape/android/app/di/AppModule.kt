package xyz.stylianosgakis.shape.android.app.di

import android.app.Application
import android.content.Context
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ProcessLifecycleOwner
import androidx.lifecycle.lifecycleScope
import com.deliveryhero.whetstone.SingleIn
import com.deliveryhero.whetstone.app.ApplicationScope
import com.squareup.anvil.annotations.ContributesTo
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineScope

@Module
@ContributesTo(ApplicationScope::class)
object AppModule {

  @SingleIn(ApplicationScope::class)
  @Provides
  fun bindContext(application: Application): Context {
    return application.applicationContext
  }

  @SingleIn(ApplicationScope::class)
  @Provides
  fun provideLifecycleOwner(): LifecycleOwner {
    return ProcessLifecycleOwner.get()
  }

  @SingleIn(ApplicationScope::class)
  @Provides
  fun provideCoroutineScope(
    lifecycleOwner: LifecycleOwner,
  ): CoroutineScope {
    return lifecycleOwner.lifecycleScope
  }
}
