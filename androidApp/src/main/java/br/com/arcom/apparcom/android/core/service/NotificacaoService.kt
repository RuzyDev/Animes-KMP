package br.com.arcom.apparcom.android.core.service

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.media.RingtoneManager
import android.net.Uri
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.core.app.NotificationCompat
import br.com.arcom.apparcom.android.MainActivity
import br.com.arcom.autoriza.android.R
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.serialization.json.Json
import org.koin.android.ext.android.inject
import java.lang.Math.random

class NotificacaoService : FirebaseMessagingService() {

    private val json: Json by inject()

    private val job = SupervisorJob()
    private val scope = CoroutineScope(Dispatchers.IO + job)


    override fun onCreate() {
        super.onCreate()
    }

    override fun onMessageReceived(remoteMessage: RemoteMessage) {

        remoteMessage.data.forEach { (key, value) ->
            sendNotification(
                "Aplicativo arcom",
                value,
                random().toInt()
            )
        }
    }

    @OptIn(DelicateCoroutinesApi::class)
    override fun onNewToken(token: String) {
    }


    private fun sendNotification(titulo: String, mensagem: String, id: Int) {
        val intent = Intent(this, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        val pendingIntent = PendingIntent.getActivity(
            this, 0 /* Request code */, intent, PendingIntent.FLAG_IMMUTABLE
        )
        val defaultSoundUri: Uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
        val notificationBuilder: NotificationCompat.Builder =
            NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_logo)
                .setColor(Color(0xFF448CC2).toArgb())
                .setContentTitle(titulo).setContentText(mensagem).setAutoCancel(true)
                .setSound(defaultSoundUri).setContentIntent(pendingIntent)
        val notificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        val channel = NotificationChannel(
            CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT
        )
        notificationManager.createNotificationChannel(channel)
        notificationManager.notify(id, notificationBuilder.build())
    }

    override fun onDestroy() {
        super.onDestroy()
        job.cancel()
    }


    companion object {
        private const val CHANNEL_ID = "COMUNICA_ARCOM"
        private const val CHANNEL_NAME = "COMUNICA_ARCOM"
    }

}