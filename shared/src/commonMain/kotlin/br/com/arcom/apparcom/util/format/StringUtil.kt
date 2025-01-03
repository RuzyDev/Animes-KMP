package br.com.arcom.apparcom.util.format

fun String?.isNumberOrEmpty() =
    if (this?.isNotEmpty() == true) this.toDoubleOrNull() != null else true