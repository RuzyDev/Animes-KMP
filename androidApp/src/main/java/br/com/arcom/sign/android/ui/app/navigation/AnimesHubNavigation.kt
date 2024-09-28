package br.com.arcom.sign.app.navigation

abstract class AnimesHubNavigation(private val route: String) {
    open fun getRoute() = route
}