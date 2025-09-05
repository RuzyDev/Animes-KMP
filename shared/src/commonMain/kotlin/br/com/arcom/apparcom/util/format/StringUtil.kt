package br.com.arcom.apparcom.util.format

fun String?.isNumberOrEmpty() =
    if (this?.isNotEmpty() == true) this.toDoubleOrNull() != null else true

fun String.getQtdPalavras(qtd: Int = 2): String {
    val words = this.split(" ").filter { it.isNotEmpty() }
    val quantidade = if (words.size < qtd) words.size else qtd
    return words.take(quantidade).joinToString(" ")
}

fun String.toNome() = this.split(" ")
    .joinToString(" ") { it.lowercase().replaceFirstChar { char -> char.uppercase() } }

fun String?.toStringApp() = if (this.isNullOrEmpty()) "" else this