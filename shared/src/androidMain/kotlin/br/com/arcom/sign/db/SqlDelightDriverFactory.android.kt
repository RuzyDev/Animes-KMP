package br.com.arcom.sign.db

import android.content.Context
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import br.com.arcom.sign.database.AnimesHubDatabase

actual class SqlDelightDriverFactory(
    private val context: Context,
) {
    actual fun createDriver(): SqlDriver {
        return AndroidSqliteDriver(AnimesHubDatabase.Schema, context, "animeshub.db")
    }
}
