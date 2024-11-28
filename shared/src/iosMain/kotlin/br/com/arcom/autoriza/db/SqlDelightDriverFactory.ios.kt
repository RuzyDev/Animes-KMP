package br.com.arcom.autoriza.db

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.native.NativeSqliteDriver
import br.com.arcom.autoriza.database.AutorizaDatabase

actual class SqlDelightDriverFactory {
    actual fun createDriver(): SqlDriver {
        return NativeSqliteDriver(AutorizaDatabase.Schema, "autoriza.db")
    }
}
