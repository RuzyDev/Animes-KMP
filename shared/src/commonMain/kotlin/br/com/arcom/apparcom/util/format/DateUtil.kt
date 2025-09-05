package br.com.arcom.apparcom.util.format

import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.LocalTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.format
import kotlinx.datetime.format.FormatStringsInDatetimeFormats
import kotlinx.datetime.format.byUnicodePattern
import kotlinx.datetime.toLocalDateTime
import kotlin.time.ExperimentalTime

@OptIn(FormatStringsInDatetimeFormats::class)
fun LocalDateTime.formatBrasil() = this.format(LocalDateTime.Format { byUnicodePattern("dd/MM/yyyy HH:mm:ss") })

@OptIn(ExperimentalTime::class)
fun dataHoraAtual(): LocalDateTime{
    val current = kotlin.time.Clock.System.now()
    return current.toLocalDateTime(TimeZone.currentSystemDefault())
}

fun getPeriodoDia(): String {
    val horaAtual = dataHoraAtual().time

    val manhaInicio = LocalTime(6, 0)
    val tardeInicio = LocalTime(12, 0)
    val noiteInicio = LocalTime(18, 0)

    return when {
        horaAtual >= manhaInicio && horaAtual < tardeInicio -> "Bom dia"
        horaAtual >= tardeInicio && horaAtual < noiteInicio -> "Boa tarde"
        else -> "Boa noite"
    }
}