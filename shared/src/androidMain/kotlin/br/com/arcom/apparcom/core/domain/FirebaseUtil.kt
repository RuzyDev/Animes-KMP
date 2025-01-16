package br.com.arcom.apparcom.core.domain

import com.google.firebase.messaging.FirebaseMessaging
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class FirebaseUtil(
    private val firebaseMessaging: FirebaseMessaging
) {
    suspend fun getToken(): String? =
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