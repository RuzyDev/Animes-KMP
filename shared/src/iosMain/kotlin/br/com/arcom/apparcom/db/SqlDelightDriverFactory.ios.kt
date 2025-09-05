package br.com.arcom.apparcom.db

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.native.NativeSqliteDriver
import br.com.arcom.apparcom.database.AppAnimeDatabase

actual class SqlDelightDriverFactory {
    actual fun createDriver(): SqlDriver {
        return NativeSqliteDriver(AppAnimeDatabase.Schema, "apparcom.db")
    }
}
