package br.com.arcom.sign.network.util

import br.com.arcom.sign.network.models.NetworkErro
import br.com.arcom.sign.network.models.NetworkPlayError
import br.com.arcom.sign.util.ResultState
import io.ktor.client.call.body
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.RedirectResponseException
import io.ktor.client.plugins.ServerResponseException
import io.ktor.client.statement.HttpResponse
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

internal suspend fun parseNetworkError(
    errorResponse: HttpResponse? = null,
    exception: Exception? = null
): Exception {
    val error = errorResponse?.body<NetworkPlayError>()
    throw Exception(error?.erro?.message ?: exception?.message)
}
