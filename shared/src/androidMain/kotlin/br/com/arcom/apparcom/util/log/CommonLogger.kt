package br.com.arcom.apparcom.util.log

import android.util.Log

actual interface CommonLogger {
    actual fun log(tag:String, message:String){
         Log.d(tag, message)
    }
}