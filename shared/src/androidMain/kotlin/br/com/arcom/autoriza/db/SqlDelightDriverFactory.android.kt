package br.com.arcom.autoriza.db

import android.content.Context
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import br.com.arcom.autoriza.database.AutorizaDatabase

actual class SqlDelightDriverFactory(
    private val context: Context,
) {
    actual fun createDriver(): SqlDriver {
        return AndroidSqliteDriver(AutorizaDatabase.Schema, context, "autoriza.db")
    }
}
