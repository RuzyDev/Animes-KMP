package br.com.arcom.apparcom.data

actual class AndroidAppAnimeFiles {
    actual suspend fun saveFile(data: ByteArray, fileName: String): String {
        return ""
    }

    actual fun deleteFilesStartsWith(prefix: String) {
    }

    actual fun getFile(fileName: String): String? {
       return ""
    }
}