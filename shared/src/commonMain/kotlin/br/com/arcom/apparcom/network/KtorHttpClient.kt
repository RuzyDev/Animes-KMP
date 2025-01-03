package br.com.arcom.apparcom.network

import br.com.arcom.apparcom.util.ConstantsShared.BASE_URL
import br.com.arcom.apparcom.data.datastore.AppArcomStorage
import br.com.arcom.apparcom.data.datastore.Keys
import br.com.arcom.apparcom.data.preferences.KeysPreferences
import br.com.arcom.apparcom.data.preferences.PreferencesManager
import br.com.arcom.apparcom.network.models.RefreshTokenResponse
import br.com.arcom.apparcom.util.log.CommonLoggerImpl
import io.ktor.client.HttpClient
import io.ktor.client.plugins.HttpResponseValidator
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.auth.Auth
import io.ktor.client.plugins.auth.providers.BearerTokens
import io.ktor.client.plugins.auth.providers.bearer
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.observer.ResponseObserver
import io.ktor.client.statement.HttpResponse
import io.ktor.http.URLProtocol
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

class KtorHttpClient(
    private val appArcomStorage: AppArcomStorage,
    private val preferencesManager: PreferencesManager
) {
    var accessToken = preferencesManager.get(KeysPreferences.ACCESS_TOKEN) ?:  ""
    var refreshToken = preferencesManager.get(KeysPreferences.REFRESH_TOKEN) ?:  ""

    fun httpClient(enableNetworkLogs: Boolean) = HttpClient {
        expectSuccess = false

        defaultRequest {
            url {
                protocol = URLProtocol.HTTPS
                host = BASE_URL
            }
        }

        install(Auth){
            bearer {
                loadTokens {
                    BearerTokens(accessToken, refreshToken)
                }
                refreshTokens {
                    accessToken = preferencesManager.get(KeysPreferences.ACCESS_TOKEN) ?:  ""
                    refreshToken = preferencesManager.get(KeysPreferences.REFRESH_TOKEN) ?:  ""
                    BearerTokens(accessToken, refreshToken)
                }
            }
        }

        if (enableNetworkLogs) {
            install(Logging) {
                level = LogLevel.HEADERS
                logger = object : Logger {
                    override fun log(message: String) {
                        CommonLoggerImpl().log(tag = "Http Client", message = message)
                    }
                }
            }
        }

        install(HttpTimeout) {
            val timeout = 30000L
            connectTimeoutMillis = timeout
            requestTimeoutMillis = timeout
            socketTimeoutMillis = timeout
        }

        install(ResponseObserver) {
            onResponse { response ->
                println("AppDebug HTTP ResponseObserver status: ${response.status.value}")
            }
        }
        HttpResponseValidator {
            validateResponse { response: HttpResponse ->
                val statusCode = response.status.value

                if (statusCode == 401) {
                    appArcomStorage.emitBoolean(false, Keys.LOGADO)
                    preferencesManager.remove(KeysPreferences.ACCESS_TOKEN)
                }
            }
        }

        install(Logging) {
            //  logger = Logger.DEFAULT
            level = LogLevel.ALL
            logger = object : Logger {
                override fun log(message: String) {
                    println("AppDebug KtorHttpClient message:$message")
                }
            }
        }

        install(ContentNegotiation) {
            json(
                Json {
                    ignoreUnknownKeys = true
                    isLenient = true
                }
            )
        }
    }
}

suspend fun HttpClient.refreshAccessToken(refreshToken: String): RefreshTokenResponse {
//    val response = this.post("https://api.exemplo.com/auth/refresh") {
//        contentType(ContentType.Application.Json)
//        setBody(mapOf("refreshToken" to refreshToken))
//    }
//    return response.body<RefreshTokenResponse>()
    return RefreshTokenResponse("","")
}