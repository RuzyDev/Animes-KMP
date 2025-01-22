package br.com.arcom.apparcom.util.format

fun Long?.maiorQueZero() = (this ?: 0) > 0

fun formatBytes(bytes: Long): String {
    return if (bytes < 1024) {
        "$bytes b"
    } else if (bytes in 1024..1048575) {
        "${bytes / 1024} kB"
    } else {
        "${bytes / 1048576} MB"
    }
}