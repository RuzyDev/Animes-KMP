package br.com.arcom.apparcom

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform