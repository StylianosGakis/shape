package xyz.stylianosgakis.shape.database

import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.sqlite.driver.JdbcSqliteDriver

class DriverFactoryImpl : DriverFactory {
  override fun createDriver(): SqlDriver {
    val driver: SqlDriver = JdbcSqliteDriver(JdbcSqliteDriver.IN_MEMORY)
    ShapeDatabase.Schema.create(driver)
    return driver
  }
}
