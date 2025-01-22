package br.com.arcom.apparcom.android.core.service

import br.com.arcom.apparcom.service.TokenService
import com.google.firebase.messaging.FirebaseMessaging
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class AndroidTokenService(
    private val firebaseMessaging: FirebaseMessaging
): TokenService {
    override suspend fun getToken(): String? =
        suspendCoroutine { cont ->
            firebaseMessaging.token.addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    cont.resume(task.result)
                } else {
                    cont.resume(null)
                }
            }
        }
}