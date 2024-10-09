package br.com.arcom.autoriza.db.util

import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

fun <T> List<T>.toStringDb() = Json.encodeToString(this)
fun <T> String.toListDb() = Json.decodeFromString<List<T>>(this)