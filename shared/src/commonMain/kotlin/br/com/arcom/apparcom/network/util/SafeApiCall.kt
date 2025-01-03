package br.com.arcom.apparcom.network.util

import br.com.arcom.apparcom.network.models.NetworkPlayError
import io.ktor.client.call.body
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.RedirectResponseException
import io.ktor.client.plugins.ServerResponseException
import io.ktor.client.statement.HttpResponse
import io.ktor.http.HttpStatusCode
import io.ktor.util.network.UnresolvedAddressException

suspend fun <T : Any?> safeApiCall(apiCall: suspend () -> T): T {
    return try {
        apiCall.invoke()
    } catch (e: RedirectResponseException) {
        val error = parseNetworkError(e.response.body())
        throw error
    } catch (e: ClientRequestException) {
        val error = parseNetworkError(e.response.body())
        throw error
    } catch (e: ServerResponseException) {
        val error = parseNetworkError(e.response.body())
        throw error
    } catch (e: UnresolvedAddressException) {
        val error = parseNetworkError(exception = e)
        throw error
    } catch (e: Exception) {
        val error = parseNetworkError(exception = e)
        throw error
    }
}

suspend inline fun <reified T> HttpResponse.bodyOrNull(): T? {
    return when (this.status) {
        HttpStatusCode.NoContent -> null // Trata respostas 204 como nulas.
        HttpStatusCode.Unauthorized -> throw Exception("Não autorizado!")
        else -> this.body<T>() // Tenta processar o corpo da resposta para outros status válidos.
    }
}

internal suspend fun parseNetworkError(
    errorResponse: HttpResponse? = null,
    exception: Exception? = null
): Exception {
    val error = errorResponse?.body<NetworkPlayError>()
    throw Exception(error?.erro?.message ?: exception?.message)
}
