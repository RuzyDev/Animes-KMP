package br.com.arcom.apparcom.network.service

interface AppService {
      suspend fun downloadFile(url: String, fileName: String, onDowload: (Long, Long) -> Unit, saveFile: suspend (ByteArray, String) -> String): String
      suspend fun getVersaoApp(idUsuario: Long): String?
}
