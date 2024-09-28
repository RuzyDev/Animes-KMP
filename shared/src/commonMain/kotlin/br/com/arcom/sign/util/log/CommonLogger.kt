package br.com.arcom.sign.util.log

expect interface CommonLogger {
    open fun log(tag: String, message: String)
}