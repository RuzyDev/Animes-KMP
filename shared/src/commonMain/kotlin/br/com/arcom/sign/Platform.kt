package br.com.arcom.sign

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform