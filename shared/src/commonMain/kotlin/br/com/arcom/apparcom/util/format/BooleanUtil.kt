package br.com.arcom.apparcom.util.format

fun Boolean?.toLong() = if (this == true) 1L else 0L