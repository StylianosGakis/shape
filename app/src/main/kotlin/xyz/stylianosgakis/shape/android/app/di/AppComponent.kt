package xyz.stylianosgakis.shape.android.app.di

import android.app.Application
import com.deliveryhero.whetstone.SingleIn
import com.deliveryhero.whetstone.app.ApplicationComponent
import com.deliveryhero.whetstone.app.ApplicationScope
import com.squareup.anvil.annotations.MergeComponent
import dagger.BindsInstance
import dagger.Component

@SingleIn(ApplicationScope::class)
@MergeComponent(ApplicationScope::class)
interface AppComponent : ApplicationComponent {
  @Component.Factory
  interface Factory : ApplicationComponent.Factory {
    override fun create(
      @BindsInstance application: Application,
    ): AppComponent
  }
}
