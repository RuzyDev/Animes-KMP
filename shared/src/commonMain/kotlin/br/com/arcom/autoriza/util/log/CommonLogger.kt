package br.com.arcom.autoriza.util.log

expect interface CommonLogger {
    open fun log(tag: String, message: String)
}