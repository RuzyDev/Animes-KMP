package br.com.arcom.autoriza.db

import androidx.room.RoomDatabaseConstructor
import app.cash.sqldelight.db.SqlDriver

@Suppress("NO_ACTUAL_FOR_EXPECT")
expect object AppDatabaseConstructor : RoomDatabaseConstructor<AppDatabase> {
    override fun initialize(): AppDatabase
}
