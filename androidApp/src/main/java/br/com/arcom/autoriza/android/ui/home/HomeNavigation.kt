package br.com.arcom.autoriza.android.ui.home

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import br.com.arcom.autoriza.app.navigation.AnimesHubNavigation

object HomeNavigation: AnimesHubNavigation("home")

fun NavGraphBuilder.home(){
    composable(
        route = HomeNavigation.getRoute()
    ) {
        HomeRoute()
    }
}