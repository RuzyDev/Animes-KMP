package br.com.arcom.sign.db

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.native.NativeSqliteDriver
import br.com.arcom.sign.database.AnimesHubDatabase

actual class SqlDelightDriverFactory {
    actual fun createDriver(): SqlDriver {
        return NativeSqliteDriver(AnimesHubDatabase.Schema, "animeshub.db")
    }
}
