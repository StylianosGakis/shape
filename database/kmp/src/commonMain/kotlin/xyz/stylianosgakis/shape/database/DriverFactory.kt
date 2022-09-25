package xyz.stylianosgakis.shape.database

import com.squareup.sqldelight.db.SqlDriver

interface DriverFactory {
  fun createDriver(): SqlDriver
}
