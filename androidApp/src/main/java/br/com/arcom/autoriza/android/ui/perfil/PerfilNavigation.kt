package br.com.arcom.autoriza.android.ui.perfil

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

@Serializable
object PerfilNavigation

fun NavController.navigateToPerfil(navOptions: NavOptions) =
    navigate(route = PerfilNavigation, navOptions)

fun NavGraphBuilder.perfil(){
    composable<PerfilNavigation> {
        PerfilRoute()
    }
}