package br.com.arcom.autoriza.android.ui.app.navigation

abstract class AutorizaNavigation(private val route: String) {
    open fun getRoute() = route
}