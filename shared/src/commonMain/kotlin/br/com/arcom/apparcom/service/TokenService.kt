package br.com.arcom.apparcom.service

interface TokenService {
    suspend fun getToken(): String?
}