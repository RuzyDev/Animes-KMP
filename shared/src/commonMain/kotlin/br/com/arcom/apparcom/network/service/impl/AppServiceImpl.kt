package br.com.arcom.apparcom.network.service.impl

import br.com.arcom.apparcom.network.service.AppService
import br.com.arcom.apparcom.network.util.bodyOrNull
import br.com.arcom.apparcom.network.util.safeApiCall
import io.ktor.client.HttpClient
import io.ktor.client.plugins.onDownload
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.readBytes
import io.ktor.http.ContentType
import io.ktor.http.contentType

class AppServiceImpl(
    private val api: HttpClient
) : AppService {
    override suspend fun downloadFile(
        url: String,
        fileName: String,
        onDowload: (Long, Long) -> Unit,
        saveFile: suspend (ByteArray, String) -> String
    ): String {
        // Baixa os bytes do arquivo
        val response: HttpResponse = api.get(url){
            onDownload { bytesSentTotal, contentLength ->
                onDowload(bytesSentTotal, contentLength)
            }
        }
        val fileBytes = response.readBytes()

        // Salva o arquivo usando o FileSaver
        return saveFile(fileBytes, fileName)
    }

    override suspend fun getVersaoApp(idUsuario: Long): String? {
        return safeApiCall {
            api.get(urlString = "api/v1/versao-app") {
                parameter("idFuncionario", 1)
                parameter("app", "apparcom")
                contentType(ContentType.Application.Json)
            }.bodyOrNull<String>()
        }
    }
}