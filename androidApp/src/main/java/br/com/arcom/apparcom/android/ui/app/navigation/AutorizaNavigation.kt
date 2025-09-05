package br.com.arcom.apparcom.android.ui.app.navigation

abstract class AppAnimeNavigation(private val route: String) {
    open fun getRoute() = route
}