package br.com.arcom.autoriza

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform