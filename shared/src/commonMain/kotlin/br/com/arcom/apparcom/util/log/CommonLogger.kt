package br.com.arcom.apparcom.util.log

expect interface CommonLogger {
    open fun log(tag: String, message: String)
}