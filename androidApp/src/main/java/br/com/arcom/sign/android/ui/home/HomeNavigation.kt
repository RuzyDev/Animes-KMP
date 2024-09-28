package br.com.arcom.sign.android.ui.home

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import br.com.arcom.sign.app.navigation.AnimesHubNavigation

object HomeNavigation: AnimesHubNavigation("home")

fun NavGraphBuilder.home(){
    composable(
        route = HomeNavigation.getRoute()
    ) {
        HomeRoute()
    }
}