package br.com.arcom.autoriza.app.navigation

import AnimesHubIcons

data class TopLevelDestination(
    val routeNavigation: String,
    val icon: AnimesHubIcons,
    val text: Int
) : AnimesHubNavigation(routeNavigation)
