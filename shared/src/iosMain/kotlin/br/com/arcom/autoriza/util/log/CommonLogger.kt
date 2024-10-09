package br.com.arcom.autoriza.util.log

import kotlin.experimental.ExperimentalNativeApi

actual interface CommonLogger {
    @OptIn(ExperimentalNativeApi::class)
    actual fun log(tag: String, message:String){
        if(Platform.isDebugBinary){
           //NSLog("\\%\\@: $message", "DEBUG")
        }
    }
}