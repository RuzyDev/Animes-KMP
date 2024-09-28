package br.com.arcom.sign.db

import app.cash.sqldelight.db.SqlDriver

expect class SqlDelightDriverFactory {
    fun createDriver(): SqlDriver
}
