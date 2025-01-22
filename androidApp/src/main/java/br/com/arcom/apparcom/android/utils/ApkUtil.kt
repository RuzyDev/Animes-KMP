package br.com.arcom.apparcom.android.utils

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.core.content.FileProvider
import java.io.File

/**
 * Instala um aplicativo (APK) a partir de um arquivo fornecido.
 *
 * @param context O contexto da aplicação.
 * @param file O caminho do arquivo APK a ser instalado.
 * @param emitMessage Uma função de retorno que recebe uma string.
 *                    Usada para relatar mensagens de erro durante a instalação.
 */
fun instalarApk(context: Context, file: String, callbackError: (String) -> Unit) {
    try {
        val file = File(file)
        if (file.exists()) {
            val apkUri: Uri = FileProvider.getUriForFile(
                context,
                "${context.packageName}.fileprovider", // Certifique-se de usar o mesmo `authorities` configurado no manifest
                file
            )

            val intent = Intent(Intent.ACTION_VIEW).apply {
                setDataAndType(apkUri, "application/vnd.android.package-archive")
                flags = Intent.FLAG_GRANT_READ_URI_PERMISSION or Intent.FLAG_ACTIVITY_NEW_TASK
            }

            context.startActivity(intent)
        }
    } catch (e: Exception) {
        e.printStackTrace()
        callbackError(e.message ?: "Ocorreu um erro")
    }
}