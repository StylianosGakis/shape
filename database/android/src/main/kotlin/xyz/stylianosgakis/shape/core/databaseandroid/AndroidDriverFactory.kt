package xyz.stylianosgakis.shape.core.databaseandroid

import android.content.Context
import com.deliveryhero.whetstone.SingleIn
import com.deliveryhero.whetstone.app.ApplicationScope
import com.squareup.anvil.annotations.ContributesBinding
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver
import xyz.stylianosgakis.shape.database.DriverFactory
import xyz.stylianosgakis.shape.database.ShapeDatabase
import javax.inject.Inject

@SingleIn(ApplicationScope::class)
@ContributesBinding(ApplicationScope::class)
class AndroidDriverFactory @Inject constructor(
  private val context: Context,
) : DriverFactory {
  override fun createDriver(): SqlDriver {
    return AndroidSqliteDriver(ShapeDatabase.Schema, context, "ShapeDatabase.db")
  }
}
