package br.com.arcom.autoriza.app.navigation

abstract class AnimesHubNavigation(private val route: String) {
    open fun getRoute() = route
}