package br.com.arcom.apparcom.data

expect class AndroidAppAnimeFiles {
    fun getFile(fileName: String): String?
    suspend fun saveFile(data: ByteArray, fileName: String): String
    fun deleteFilesStartsWith(prefix: String)
}