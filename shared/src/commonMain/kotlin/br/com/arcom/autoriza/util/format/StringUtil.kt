package br.com.arcom.autoriza.util.format

fun String?.isNumberOrEmpty() =
    if (this?.isNotEmpty() == true) this.toDoubleOrNull() != null else true