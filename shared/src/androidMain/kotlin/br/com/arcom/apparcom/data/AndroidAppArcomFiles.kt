package br.com.arcom.apparcom.data

import android.content.Context
import android.os.Environment
import java.io.File

actual class AndroidAppArcomFiles(
    private val context: Context
) {
    private val downloadsDir: File = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
    private val downloads = File(downloadsDir, "apparcom")
    private val cache = context.cacheDir

    actual suspend fun saveFile(data: ByteArray, fileName: String): String {
        // Diretório público de Downloads

        val file = File(cache.absolutePath + "/$fileName")

        // Escreve os dados no arquivo
        file.outputStream().use { outputStream ->
            outputStream.write(data)
        }

        return file.absolutePath // Retorna o caminho do arquivo salvo
    }

    actual fun deleteFilesStartsWith(prefix: String) {
        downloadsDir.listFiles()?.filter { it.name.startsWith(prefix, true) }
            ?.forEach { it.deleteRecursively() }
    }

    actual fun getFile(fileName: String): String? {
        val file = File(cache.absolutePath + "/$fileName")
        return if(file.exists()) file.absolutePath else null
    }
}