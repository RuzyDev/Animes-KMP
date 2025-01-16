package br.com.arcom.apparcom.android.utils

import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

private suspend fun getToken(): String? =
    suspendCoroutine { cont ->
        firebaseMessaging.token.addOnCompleteListener { task ->
            if (task.isSuccessful) {
                cont.resume(task.result)
            } else {
                cont.resume(null)
            }
        }
    }