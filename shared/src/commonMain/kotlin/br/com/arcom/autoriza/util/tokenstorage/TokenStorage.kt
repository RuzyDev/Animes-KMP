package br.com.arcom.autoriza.util.tokenstorage

interface TokenStorage {
    fun saveToken(token: String)
    fun getToken(): String?
    fun clearToken()
}