package br.com.arcom.apparcom.db

import android.content.Context
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import br.com.arcom.apparcom.database.AppAnimeDatabase

actual class SqlDelightDriverFactory(
    private val context: Context,
) {
    actual fun createDriver(): SqlDriver {
        return AndroidSqliteDriver(AppAnimeDatabase.Schema, context, "apparcom.db")
    }
}
