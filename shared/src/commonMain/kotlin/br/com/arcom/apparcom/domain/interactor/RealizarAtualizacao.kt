package br.com.arcom.apparcom.domain.interactor

import br.com.arcom.apparcom.data.AndroidAppAnimeFiles
import br.com.arcom.apparcom.network.service.AppService
import br.com.arcom.apparcom.util.AppCoroutineDispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.withContext

class RealizarAtualizacao(
    private val appService: AppService,
    private val dispatchers: AppCoroutineDispatchers,
    private val androidAppAnimeFiles: AndroidAppAnimeFiles
) {
    val _progressFlow = MutableStateFlow<Pair<Long, Long>>(Pair(0, 0))

    suspend fun baixar(ultimaVersao: String, baixarNovamente: Boolean = false): String {
        return withContext(dispatchers.io) {
            val fileName = "apparcom-$ultimaVersao.apk"
            val url = "https://cdn02.arcom.com.br/appDownload/apparcom/$fileName"

            val file = androidAppAnimeFiles.getFile(fileName)

            if (file == null || baixarNovamente){
                appService.downloadFile(
                    url = url,
                    fileName = fileName,
                    onDowload = { bytesSentTotal, contentLength ->
                        _progressFlow.value = Pair(bytesSentTotal, contentLength)
                    },
                    saveFile = { bytes, fileName ->
                        androidAppAnimeFiles.deleteFilesStartsWith("apparcom")
                        androidAppAnimeFiles.saveFile(bytes, fileName)
                    }
                )
            }else{
                file
            }
        }
    }
}