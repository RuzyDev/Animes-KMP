package br.com.arcom.apparcom.db.util

import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

fun List<Long>.toStringDb() = this.joinToString(separator = ";")
fun String.toListLongDb() = this.split(";").mapNotNull { it.toLongOrNull() }