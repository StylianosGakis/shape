package xyz.stylianosgakis.shape.core.databaseandroid

import com.deliveryhero.whetstone.SingleIn
import com.deliveryhero.whetstone.app.ApplicationScope
import com.squareup.anvil.annotations.ContributesTo
import com.squareup.sqldelight.db.SqlDriver
import dagger.Module
import dagger.Provides
import xyz.stylianosgakis.shape.database.DriverFactory

@Suppress("unused")
@Module
@ContributesTo(ApplicationScope::class)
object DatabaseModule {
  @SingleIn(ApplicationScope::class)
  @Provides
  fun provideSqlDriver(
    driverFactory: DriverFactory,
  ): SqlDriver {
    return driverFactory.createDriver()
  }
}
