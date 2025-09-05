package br.com.arcom.apparcom.android.ui.screens.home

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

@Serializable
object HomeNavigation

fun NavController.navigateToHome(navOptions: NavOptions) =
    navigate(route = HomeNavigation, navOptions)

fun NavGraphBuilder.home(
) {
    composable<HomeNavigation> {
        HomeRoute()
    }
}