package br.com.arcom.autoriza.db

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.native.NativeSqliteDriver
import br.com.arcom.autoriza.database.AppArcomDatabase

actual class SqlDelightDriverFactory {
    actual fun createDriver(): SqlDriver {
        return NativeSqliteDriver(AppArcomDatabase.Schema, "autoriza.db")
    }
}
