package br.com.arcom.autoriza.android.ui.app.navigation

import AutorizaIcons
import br.com.arcom.autoriza.android.ui.app.navigation.AutorizaNavigation

data class TopLevelDestination(
    val routeNavigation: String,
    val icon: AutorizaIcons,
    val text: Int
) : AutorizaNavigation(routeNavigation)
