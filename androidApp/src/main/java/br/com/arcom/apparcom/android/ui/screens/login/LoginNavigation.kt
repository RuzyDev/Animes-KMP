package br.com.arcom.apparcom.android.ui.screens.login

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

@Serializable
object LoginNavigation

fun NavController.navigateToLogin(navOptions: NavOptions) =
    navigate(route = LoginNavigation, navOptions)

fun NavGraphBuilder.login(
    navigateToHome: () -> Unit
) {
    composable<LoginNavigation> {
        LoginRoute()
    }
}