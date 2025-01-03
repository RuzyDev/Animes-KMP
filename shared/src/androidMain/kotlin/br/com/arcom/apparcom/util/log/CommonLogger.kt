package br.com.arcom.apparcom.util.log

actual interface CommonLogger {
    actual fun log(tag:String, message:String){
//        if(BuildConfig.DEBUG){
//            Log.d(BuildConfig.BUILD_TYPE, message)
//        }
    }
}